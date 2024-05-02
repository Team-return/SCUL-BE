package scul.projectscul.global.security.error

import scul.projectscul.global.security.error.exception.ErrorCode
import scul.projectscul.global.security.error.exception.SculException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionFilter (
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: SculException) {
            writerErrorCode(response, e.errorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            writerErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

    @Throws(IOException::class)
    private fun writerErrorCode(response: HttpServletResponse, errorCode: ErrorCode) {
        response.apply {
            status = errorCode.status
            characterEncoding = "UTF-8"
            contentType = "application/json"
            writer.write(objectMapper.writeValueAsString(ErrorResponse(errorCode.status, errorCode.message)))
            writer.flush()
        }
    }
}