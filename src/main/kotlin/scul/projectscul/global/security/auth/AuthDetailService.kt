package scul.projectscul.global.security.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import scul.projectscul.domain.user.facade.UserFacade

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(accountId: String): UserDetails {
        val user = userFacade.getUserByAccountId(accountId)
        return AuthDetails(user.accountId)
    }
}
