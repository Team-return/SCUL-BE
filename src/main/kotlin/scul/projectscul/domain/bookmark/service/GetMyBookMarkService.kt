package scul.projectscul.domain.bookmark.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.facade.CultureFacade
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDate
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class GetMyBookMarkService (
        private val userFacade: UserFacade,
        private val bookMarkRepository: BookMarkRepository,
        private val cultureFacade: CultureFacade
) {

    fun execute(): GetCultureListResponse {
        val currentUser = userFacade.getCurrentUser()

        val bookMarkList = bookMarkRepository.findBookMarksByUser(currentUser)

        val cultureList = bookMarkList.mapNotNull { it.culture }

        val now = LocalDateTime.now()

        return GetCultureListResponse(
                cultureList.map { cultureItem ->
                    val wantedPeople = cultureItem.wantedPeople.drop(1) //첫 번째 글자 제거함

                    val isApplicationAble = cultureFacade.formatApplicationTime(cultureItem, now)

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
