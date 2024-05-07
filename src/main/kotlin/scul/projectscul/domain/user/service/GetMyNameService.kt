package scul.projectscul.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.user.facade.UserFacade
import scul.projectscul.domain.user.presentation.response.GetMyNameResponse

@Service
@Transactional(readOnly = true)
class GetMyNameService (
        private val userFacade: UserFacade
) {
    fun execute(): GetMyNameResponse {
        val currentUser = userFacade.getCurrentUser()

        return GetMyNameResponse(currentUser.name)
    }
}