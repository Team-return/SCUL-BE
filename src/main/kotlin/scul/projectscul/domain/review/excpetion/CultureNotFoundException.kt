package scul.projectscul.domain.user.exception

import scul.projectscul.global.security.error.exception.ErrorCode
import scul.projectscul.global.security.error.exception.SculException

object PasswordMisMatchException : SculException(
        ErrorCode.PASSWORD_MISMATCH
)