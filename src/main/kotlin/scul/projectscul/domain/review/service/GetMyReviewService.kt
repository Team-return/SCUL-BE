package scul.projectscul.domain.review.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.review.domain.repository.ReviewRepository
import scul.projectscul.domain.review.presentation.dto.response.GetReviewsResponse
import scul.projectscul.domain.user.facade.UserFacade

@Service
@Transactional(readOnly = true)
class GetMyReviewService (
        private val reviewRepository: ReviewRepository,
        private val userFacade: UserFacade
) {

    fun execute(): GetReviewsResponse? {
        val currentUser = userFacade.getCurrentUser()
        return reviewRepository.findReviewsByUser(currentUser)?.let {
            GetReviewsResponse(
                it
                        .map {
                            GetReviewsResponse.ReviewsResponse(it)
                        }
        )
        }
    }
}
