package scul.projectscul.domain.bookmark.domain

import jakarta.persistence.*
import scul.projectscul.domain.bookmark.BaseUUIDEntity
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.user.domain.User
import java.util.*
@Entity
class BookMark (

        id: UUID?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "culture_id")
        val culture: Culture?

) : BaseUUIDEntity(id)