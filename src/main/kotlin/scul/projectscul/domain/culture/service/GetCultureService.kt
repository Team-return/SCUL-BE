package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.facade.CultureFacade
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureResponse
import scul.projectscul.domain.review.excpetion.CultureNotFoundException
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Service
@Transactional(readOnly = true)
class GetCultureService (
        private val bookMarkRepository: BookMarkRepository,
        private val cultureRepository: CultureRepository,
        private val userFacade: UserFacade,
        private val cultureFacade: CultureFacade
) {
    fun execute(cultureId: UUID): GetCultureResponse {

        val now = LocalDate.now()

        val currentUser: User = userFacade.getCurrentUser()
        val culture: Culture = cultureRepository.findCultureById(cultureId) ?: throw CultureNotFoundException
        val isBookMarked = bookMarkRepository.existsByCultureAndUser(culture ,currentUser)

        val isApplicationAble = cultureFacade.formatApplicationTime(culture, now)

        return GetCultureResponse.of(culture, isBookMarked, isApplicationAble)
    }
}
