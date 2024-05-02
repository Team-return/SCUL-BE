package scul.projectscul.global.security.error.exception

abstract class SculException(
    val errorCode: ErrorCode
): RuntimeException()