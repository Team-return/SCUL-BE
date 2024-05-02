package scul.projectscul.domain.user.service

import scul.projectscul.global.security.dto.response.TokenResponse
import jakarta.transaction.Transactional
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.domain.repository.UserRepository
import scul.projectscul.domain.user.exception.UserAlreadyExistsException
import scul.projectscul.domain.user.presentation.request.SignUpRequest
import org.springframework.stereotype.Service
import scul.projectscul.global.security.jwt.JwtTokenProvider

@Service
@Transactional
class SignUpService (
        private val userRepository: UserRepository,
        private val jwtTokenProvider: JwtTokenProvider,
){

    fun execute(request: SignUpRequest) : TokenResponse {
        if (userRepository.existsByAccountId(request.accountId)){
            throw UserAlreadyExistsException
        }

        userRepository.save(
                User(
                        id = null,
                        name = request.name,
                        accountId = request.accountId,
                        password = request.password
                )
        )
        return jwtTokenProvider.generateToken(request.accountId)
    }
}
