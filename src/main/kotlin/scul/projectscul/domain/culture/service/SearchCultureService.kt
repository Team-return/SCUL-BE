package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.facade.UserFacade

@Service
@Transactional(readOnly = true)
class SearchCultureService (
        private val cultureRepository: CultureRepository,
        private val bookMarkRepository: BookMarkRepository,
        private val userFacade: UserFacade
) {
    fun execute(keyword: String): GetCultureListResponse {
        val culture: List<Culture> = cultureRepository.findByCultureNameContainingOrContentContaining(keyword = keyword, keyword2 = keyword)
        val currentUser: User = userFacade.getCurrentUser()

        return GetCultureListResponse(
                culture.map { cultureItem ->
                    val isBookMarked: Boolean = bookMarkRepository.existsByCultureAndUser(cultureItem, currentUser)
                    val wantedPeople = cultureItem.wantedPeople.drop(1) //첫 번째 글자 제거함

                    GetCultureListResponse.CultureListResponse(
                            id = cultureItem.id,
                            location = cultureItem.location,
                            placeName = cultureItem.placeName,
                            ticketPrice = cultureItem.ticketPrice,
                            isBookMarked = isBookMarked,
                            imageUrl = cultureItem.imageUrl,
                            cultureName = cultureItem.cultureName,
                            wantedPeople = wantedPeople,
                            applicationStartDate = cultureItem.applicationStartDate,
                            applicationEndDate = cultureItem.applicationEndDate
                    )
                }
        )
    }
}