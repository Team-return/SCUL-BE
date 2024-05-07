package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
@Transactional(readOnly = true)
class GetCultureListService (
        private val cultureRepository: CultureRepository,
        private val bookMarkRepository: BookMarkRepository,
        private val userFacade: UserFacade
) {
    fun execute(): GetCultureListResponse {
        val culture: List<Culture> = cultureRepository.findAll()
        val currentUser: User = userFacade.getCurrentUser()

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")


        return GetCultureListResponse(
                culture.map { cultureItem ->
                    val isBookMarked: Boolean = bookMarkRepository.existsByCultureAndUser(cultureItem, currentUser)
                    val wantedPeople = cultureItem.wantedPeople.drop(1) //첫 번째 글자 제거함
                    val now = LocalDateTime.now()

                    val applicationStartDate: LocalDateTime = LocalDateTime.parse(cultureItem.applicationStartDate, formatter)
                    val applicationEndDate: LocalDateTime = LocalDateTime.parse(cultureItem.applicationEndDate, formatter)

                    val isApplicationAble = now >= applicationStartDate && now <= applicationEndDate

                    GetCultureListResponse.CultureListResponse(
                            id = cultureItem.id,
                            location = cultureItem.location,
                            placeName = cultureItem.placeName,
                            ticketPrice = cultureItem.ticketPrice,
                            isBookMarked = isBookMarked,
                            imageUrl = cultureItem.imageUrl,
                            cultureName = cultureItem.cultureName,
                            wantedPeople = wantedPeople,
                            isApplicationAble = isApplicationAble
                    )
                }
        )
    }
}