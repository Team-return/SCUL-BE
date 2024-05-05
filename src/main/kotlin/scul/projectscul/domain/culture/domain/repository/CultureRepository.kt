package scul.projectscul.domain.culture.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.user.domain.User
import java.util.*

interface CultureRepository: JpaRepository<Culture, UUID> {

    fun findCultureById(cultureId: UUID) : Culture

    fun findByWantedPeopleContaining(tag: String) : List<Culture>

    fun findByCultureNameContainingOrContentContaining(keyword: String, keyword2: String): List<Culture>

    fun findCulturesByWantedPeopleContainingAndCultureNameContaining(wantedPeople: String?, cultureName: String?) : List<Culture>
}
