package scul.projectscul.domain.culture.facade

import org.springframework.stereotype.Component
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.culture.domain.repository.CultureRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class CultureFacade(
        private val cultureRepository: CultureRepository

) {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun formatApplicationTime(cultureItem: Culture, now: LocalDate): Boolean {
        val applicationStartDate: LocalDate = LocalDate.parse(cultureItem.applicationStartDate, formatter)
        val applicationEndDate: LocalDate = LocalDate.parse(cultureItem.applicationEndDate, formatter)

        return now >= applicationStartDate && now <= applicationEndDate
    }
}