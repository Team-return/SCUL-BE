package scul.projectscul.global.security.jwt

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

@Component
data class JwtProperties(
        @Value("\${jwt.header}") val header: String,
        @Value("\${jwt.prefix}") val prefix: String,
        @Value("\${jwt.secret-key}") val secretKey: String,
        @Value("\${jwt.access-exp}") val accessExp: Long,
        @Value("\${jwt.refresh-exp}") val refreshExp: Long
){
    val secretKey2: SecretKey = Keys.hmacShaKeyFor(
            Base64.getEncoder().encodeToString(secretKey.toByteArray())
                    .toByteArray(Charsets.UTF_8)
    )
}
