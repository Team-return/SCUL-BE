package scul.projectscul.domain.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import scul.projectscul.global.security.dto.response.TokenResponse
import scul.projectscul.domain.user.presentation.request.SignUpRequest
import scul.projectscul.domain.user.service.SignUpService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import scul.projectscul.domain.user.presentation.request.LoginRequest
import scul.projectscul.domain.user.presentation.response.GetMyNameResponse
import scul.projectscul.domain.user.service.GetMyNameService
import scul.projectscul.domain.user.service.LoginService

@RequestMapping("/scul/users")
@RestController
class UserController (
        private val signUpService: SignUpService,
        private val loginService: LoginService,
        private val getMyNameService: GetMyNameService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest) : TokenResponse {
        return signUpService.execute(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) : TokenResponse {
        return loginService.execute(request)
    }

    @GetMapping("/name")
    fun getMyName(): GetMyNameResponse {
        return getMyNameService.execute()
    }
}
