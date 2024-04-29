package scul.projectscul.global.security.auth

import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(accountId: String): UserDetails {
        val user: User = userRepository.findByAccountId(accountId)
        return AuthDetails(user)
    }
}
