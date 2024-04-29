package scul.projectscul.global.security.exception

import scul.projectscul.global.error.ErrorCode
import com.example.kotlinpractice.global.error.exception.BusinessException


class ExpiredTokenException private constructor() : BusinessException(ErrorCode.EXPIRED_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = ExpiredTokenException()
    }
}