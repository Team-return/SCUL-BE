package scul.projectscul.domain.culture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureNameListResponse

@Service
@Transactional(readOnly = true)
class NameSearchCultureService(
        private val cultureRepository: CultureRepository,
) {
    fun execute(keyword: String): GetCultureNameListResponse {
        val culture: List<Culture> = cultureRepository.findByCultureNameContaining(keyword = keyword)
        return GetCultureNameListResponse(
                culture.map { cultureItem ->
                    GetCultureNameListResponse.CultureNameListResponse(
                            cultureName = cultureItem.cultureName
                    )
                }
        )
    }
}