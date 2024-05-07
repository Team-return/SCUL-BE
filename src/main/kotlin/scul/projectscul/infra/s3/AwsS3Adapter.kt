package scul.projectscul.infra.s3

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import scul.projectscul.domain.user.exception.UserNotFoundException
import java.io.IOException
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Component
class AwsS3Adapter(
        private val amazonS3Client: AmazonS3Client,
        @Value("\${cloud.aws.s3.bucket}")
        private val bucket: String
) {

    fun uploadImages(files: List<MultipartFile>): List<String> {
        return files.map { upload(it) }
    }

    private fun upload(file: MultipartFile): String {
        val fileName = "${UUID.randomUUID()}_${file.originalFilename}"
        inputS3(file, fileName)

        return getResourceUrl(fileName)
    }

    private fun inputS3(file: MultipartFile, fileName: String) {
        try {
            val inputStream = file.inputStream
            val objectMetadata = ObjectMetadata().apply {
                this.contentLength = file.size
                this.contentType = Mimetypes.getInstance().getMimetype(file.originalFilename)
            }

            amazonS3Client.putObject(
                    PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw UserNotFoundException
        }
    }

    fun getResourceUrl(fileName: String): String {
        return amazonS3Client.getResourceUrl(bucket, fileName)
    }

    fun getUploadUrl(fileName: String): String {
        val generatePresignedUrlRequest =
                GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(4)))

        return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString()
    }
}
