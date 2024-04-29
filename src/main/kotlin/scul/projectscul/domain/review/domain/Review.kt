package scul.projectscul.domain.review.domain

import scul.projectscul.global.entity.BaseUUIDEntity
import jakarta.persistence.*
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.user.domain.User
import java.time.LocalDate
import java.util.*

@Entity
class Review (
        id: UUID?,

        @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
        val content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "culture_id", columnDefinition = "BINARY(16)", nullable = false)
        val culture: Culture,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
        val user: User,

        @Column
        val createdAt: LocalDate

) : BaseUUIDEntity(id)
