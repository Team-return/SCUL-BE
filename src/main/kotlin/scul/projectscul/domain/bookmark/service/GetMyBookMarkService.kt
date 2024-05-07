package scul.projectscul.domain.bookmark.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
@Transactional(readOnly = true)
class GetMyBookMarkService (
        private val userFacade: UserFacade,
        private val bookMarkRepository: BookMarkRepository,
) {

    fun execute(): GetCultureListResponse {
        val currentUser = userFacade.getCurrentUser()

        val bookMarkList = bookMarkRepository.findBookMarksByUser(currentUser)

        val cultureList = bookMarkList.mapNotNull { it.culture }

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

        return GetCultureListResponse(
                cultureList.map { cultureItem ->
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
                            isBookMarked = true,
                            imageUrl = cultureItem.imageUrl,
                            cultureName = cultureItem.cultureName,
                            wantedPeople = wantedPeople,
                            isApplicationAble = isApplicationAble
                    )
                }
        )
    }
}