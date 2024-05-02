package scul.projectscul.domain.user.domain

import scul.projectscul.domain.bookmark.BaseUUIDEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity
class User(
        id : UUID?,

        @Column(columnDefinition = "VARCHAR(10)", nullable = false)
        val name: String,

        @Column(columnDefinition = "VARCHAR(30)", nullable = false)
        val accountId: String,

        @Column(columnDefinition = "CHAR(100)", nullable = false)
        val password: String,

        ) : BaseUUIDEntity(id)
