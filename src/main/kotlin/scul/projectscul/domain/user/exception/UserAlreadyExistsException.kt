package scul.projectscul.domain.user.exception

import scul.projectscul.global.security.error.exception.ErrorCode
import scul.projectscul.global.security.error.exception.SculException


object UserAlreadyExistsException : SculException(
        ErrorCode.ACCOUNT_ID_ALREADY_EXIST
)