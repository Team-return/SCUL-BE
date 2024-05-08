package scul.projectscul.domain.culture.presentation.dto.response

import java.util.UUID

data class GetCultureNameListResponse(
        val culture: List<CultureNameListResponse>
) {
    data class CultureNameListResponse(
            val cultureName: String
    )
}
