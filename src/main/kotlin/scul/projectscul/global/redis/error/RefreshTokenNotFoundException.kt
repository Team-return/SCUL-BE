package scul.projectscul.global.redis.error

import scul.projectscul.global.error.ErrorCode
import com.example.kotlinpractice.global.error.exception.BusinessException

class RefreshTokenNotFoundException private constructor(): BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND){

    companion object {
        @JvmField
        val EXCEPTION = RefreshTokenNotFoundException()
    }
}