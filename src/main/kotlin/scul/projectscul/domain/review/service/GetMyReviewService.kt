package scul.projectscul.domain.review.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.review.domain.repository.ReviewRepository
import scul.projectscul.domain.review.presentation.dto.response.GetReviewResponse
import scul.projectscul.domain.review.presentation.dto.response.GetReviewsResponse
import scul.projectscul.domain.user.facade.UserFacade

@Service
@Transactional(readOnly = true)
class GetMyReviewService (
        private val reviewRepository: ReviewRepository,
        private val userFacade: UserFacade,
        private val cultureRepository: CultureRepository
) {

    fun execute(): GetReviewResponse? {
        val currentUser = userFacade.getCurrentUser()
        return reviewRepository.findReviewsByUser(currentUser)?.let {
            GetReviewResponse(
                it
                        .map {
                            val culture = cultureRepository.findById(it.culture.id)
                            val placeName: String = culture!!.placeName
                            GetReviewResponse.ReviewResponse(it, placeName)
                        }
        )
        }
    }
}
