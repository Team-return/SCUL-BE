package scul.projectscul.domain.user.facade

import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.domain.repository.UserRepository
import scul.projectscul.domain.user.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import scul.projectscul.global.security.error.exception.TokenUnauthorizedException
import java.util.UUID

@Component
class UserFacade(
        private val userRepository: UserRepository
) {
    fun checkAccountIdExist(accountId: String): Boolean {
        return userRepository.existsByAccountId(accountId)
    }

    fun getUserId(): String? {
        val authentication =
                SecurityContextHolder.getContext().authentication ?: throw TokenUnauthorizedException()
        return authentication.name
    }

    fun getCurrentUser(): User {
        return userRepository.findByAccountId(getUserId()!!)!!
    }


    fun getUserByAccountId(accountId: String): User {
        return userRepository.findByAccountId(accountId) ?: throw UserNotFoundException
    }
}