package scul.projectscul.domain.review.excpetion

import scul.projectscul.global.security.error.exception.ErrorCode
import scul.projectscul.global.security.error.exception.SculException

object CultureNotFoundException : SculException(
        ErrorCode.CULTURE_NOT_FOUND
)