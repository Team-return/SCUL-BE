package scul.projectscul.domain.bookmark.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import scul.projectscul.domain.bookmark.domain.BookMark
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.user.domain.User
import java.util.UUID


interface BookMarkRepository: JpaRepository<BookMark, UUID> {

    fun findBookMarksByUser(user: User): List<BookMark>

    fun existsByCultureAndUser(culture: Culture, user: User) : Boolean

    fun findBookMarkByCultureAndUser(culture: Culture, user: User) : BookMark?
}
