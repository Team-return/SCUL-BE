package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.bookmark.domain.repository.BookMarkRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.facade.CultureFacade
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.user.domain.User
import scul.projectscul.domain.user.facade.UserFacade
import java.time.LocalDate
import java.time.LocalDateTime


@Service
@Transactional(readOnly = true)
class GetCultureListService (
        private val cultureRepository: CultureRepository,
        private val bookMarkRepository: BookMarkRepository,
        private val userFacade: UserFacade,
        private val cultureFacade: CultureFacade
) {
    fun execute(): GetCultureListResponse {
        val culture: List<Culture> = cultureRepository.findAll()
        val currentUser: User = userFacade.getCurrentUser()
        val now = LocalDateTime.now()


        return GetCultureListResponse(
                culture.map { cultureItem ->
                    val isBookMarked: Boolean = bookMarkRepository.existsByCultureAndUser(cultureItem, currentUser)
                    val wantedPeople = cultureItem.wantedPeople.drop(1) //첫 번째 글자 제거함

                    val isApplicationAble = cultureFacade.formatApplicationTime(cultureItem, now)

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
