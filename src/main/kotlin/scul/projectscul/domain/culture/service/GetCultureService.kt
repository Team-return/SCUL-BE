package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureResponse
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Service
@Transactional(readOnly = true)
class GetCultureService (
        private val bookMarkRepository: BookMarkRepository,
        private val cultureRepository: CultureRepository,
        private val userFacade: UserFacade
) {
    fun execute(cultureId: UUID): GetCultureResponse {

        val currentUser: User = userFacade.getCurrentUser()
        val culture: Culture = cultureRepository.findCultureById(cultureId)
        val isBookMarked = bookMarkRepository.existsByCultureAndUser(culture ,currentUser)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val now = LocalDateTime.now()

        val applicationStartDate: LocalDateTime = LocalDateTime.parse(culture.applicationStartDate, formatter)
        val applicationEndDate: LocalDateTime = LocalDateTime.parse(culture.applicationEndDate, formatter)

        val isApplicationAble = now >= applicationStartDate && now <= applicationEndDate

        return GetCultureResponse.of(culture, isBookMarked, isApplicationAble)
    }
}
