package scul.projectscul.domain.bookmark.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import scul.projectscul.domain.bookmark.domain.BookMark
import scul.projectscul.domain.bookmark.domain.BookMarkId
import scul.projectscul.domain.culture.domain.Culture
import scul.projectscul.domain.user.domain.User


interface BookMarkRepository: JpaRepository<BookMark, BookMarkId> {

    fun findBookMarksByUser(user: User): List<BookMark>

    fun existsByCultureAndUser(culture: Culture, user: User) : Boolean

    fun findBookMarkByCultureAndUser(culture: Culture, user: User) : BookMark?
}
