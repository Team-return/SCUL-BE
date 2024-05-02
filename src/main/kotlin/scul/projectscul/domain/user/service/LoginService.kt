package scul.projectscul.domain.user.service

import scul.projectscul.global.security.dto.response.TokenResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import scul.projectscul.domain.user.domain.repository.UserRepository
import scul.projectscul.domain.user.exception.PasswordMisMatchException
import scul.projectscul.domain.user.presentation.request.LoginRequest
import scul.projectscul.global.security.jwt.JwtTokenProvider

@Service
@Transactional
class LoginService (
        private val jwtProvider: JwtTokenProvider,
        private val userRepository: UserRepository
) {
    fun execute(request: LoginRequest): TokenResponse {
        val user = userRepository.findUserByAccountId(request.accountId)

        println("3kkkkkkkkkk3")
            if(request.password != user.password) {
                throw PasswordMisMatchException
            }
                return jwtProvider.generateToken(request.accountId)
    }
}
