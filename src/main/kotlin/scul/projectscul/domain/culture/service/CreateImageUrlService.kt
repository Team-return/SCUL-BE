package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import scul.projectscul.infra.s3.AwsS3Adapter
import scul.projectscul.infra.s3.ImageUrlResponse

@Service
@Transactional
class CreateImageUrlService(
        private val awsS3Adapter: AwsS3Adapter,
) {
    fun execute(multipartFiles: List<MultipartFile>): ImageUrlResponse {
        val imageUrl: List<String> = awsS3Adapter.uploadImages(multipartFiles)

        return ImageUrlResponse(imageUrl)
    }
}
