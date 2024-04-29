package scul.projectscul.domain.user.exception

import scul.projectscul.global.error.ErrorCode
import com.example.kotlinpractice.global.error.exception.BusinessException

object UserAlreadyExistsException : BusinessException(
        ErrorCode.USER_ALREADY_EXISTS
)