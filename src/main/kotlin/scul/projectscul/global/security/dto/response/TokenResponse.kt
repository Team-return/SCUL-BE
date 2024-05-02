package scul.projectscul.global.security.dto.response

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String
)