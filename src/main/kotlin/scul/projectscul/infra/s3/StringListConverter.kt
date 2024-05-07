package scul.projectscul.infra.s3

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.io.IOException


@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    companion object {
        private val mapper: ObjectMapper = ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
    }

    // DB 테이블에 들어갈 때 적용됨
    override fun convertToDatabaseColumn(attribute: List<String>?): String? {
        return try {
            // Object to JSON in String
            mapper.writeValueAsString(attribute)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException()
        }
    }

    // DB 테이블의 데이터를 Object 에 매핑시킬 때 적용됨
    override fun convertToEntityAttribute(dbData: String?): List<String>? {
        return try {
            // JSON from String to Object
            mapper.readValue(dbData, List::class.java) as List<String>?
        } catch (e: IOException) {
            throw IllegalArgumentException()
        }
    }
}