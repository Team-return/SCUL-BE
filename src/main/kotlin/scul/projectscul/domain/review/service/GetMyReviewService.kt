package scul.projectscul.domain.review.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.review.domain.repository.ReviewRepository
import scul.projectscul.domain.review.presentation.dto.response.GetReviewsResponse
import scul.projectscul.domain.user.facade.UserFacade

@Service
@Transactional(readOnly = true)
class GetMyReviewService (
        private val reviewRepository: ReviewRepository,
        private val userFacade: UserFacade,
        private val cultureRepository: CultureRepository
) {

    fun execute(): GetReviewsResponse? {
        val currentUser = userFacade.getCurrentUser()
        return reviewRepository.findReviewsByUser(currentUser)?.let {
            GetReviewsResponse(
                it
                        .map {
                            val culture = cultureRepository.findCultureById(it.culture.id)
                            val placeName: String = culture!!.placeName
                            GetReviewsResponse.ReviewsResponse(it, placeName)
                        }
        )
        }
    }
}
