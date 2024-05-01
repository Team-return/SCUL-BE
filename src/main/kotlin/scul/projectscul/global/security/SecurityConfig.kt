package scul.projectscul.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import scul.projectscul.global.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import scul.projectscul.global.error.GlobalExceptionFilter
import scul.projectscul.global.redis.repository.RefreshTokenRepository
import scul.projectscul.global.security.auth.AuthDetailsService
import scul.projectscul.global.security.jwt.JwtFilter
import scul.projectscul.global.security.jwt.JwtProperties

@EnableWebSecurity
@Configuration
class SecurityConfig(
        private val jwtProperties: JwtProperties,
        private val refreshTokenRepository: RefreshTokenRepository,
        private val authDetailsService: AuthDetailsService,
        private val jwtTokenProvider: JwtTokenProvider,
        private val objectMapper: ObjectMapper,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf { it.disable() }
                .formLogin { it.disable() }
                .cors(Customizer.withDefaults())
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http
                .authorizeHttpRequests { authorize ->
                    authorize
                            .anyRequest().permitAll()

                }
                .addFilterBefore(JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()

}
