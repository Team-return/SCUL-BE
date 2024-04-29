package scul.projectscul.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import scul.projectscul.global.error.dto.ErrorResponse
import com.example.kotlinpractice.global.error.exception.BusinessException
import com.example.kotlinpractice.global.error.exception.ErrorProperty
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            val errorCode: ErrorProperty = e.errorProperty
            response.status = errorCode.status()
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse: ErrorResponse = ErrorResponse.of(errorCode)
            objectMapper.writeValue(response.writer, errorResponse)
        }
    }
}