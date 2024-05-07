package scul.projectscul.global.security.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    //user
    TOKEN_UNAUTHORIZED(401, "Token Unauthorized"),
    PASSWORD_MISMATCH(403, "Password Mismatch"),
    USER_MISMATCH(403, "User Mismatch"),
    USER_NOT_FOUND(404, "User Not Found"),
    ACCOUNT_ID_ALREADY_EXIST(409, "AccountId Already Exist"),

    //log
    CANT_DELETE_LOG(403, "Cant Delete Log"),
    LOG_NOT_EXIST(404, "Log Not Exist"),
    ROLE_NOT_ADMIN(401,"Role Not Admin"),

    //culture
    CULTURE_NOT_FOUND(404, "Culture Not Found"),

    //file
    FILE_BAD_REQUEST(400, "File Bad Request"),

    //server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error")
}