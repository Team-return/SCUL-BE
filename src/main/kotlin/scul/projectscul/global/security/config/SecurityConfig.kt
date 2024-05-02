package scul.projectscul.global.security.config

import scul.projectscul.global.security.error.ExceptionFilter
import scul.projectscul.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import scul.projectscul.global.security.jwt.JwtTokenFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
        private val objectMapper: ObjectMapper,
        private val tokenProvider: JwtTokenProvider
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http

                .csrf { it.disable() }
                .formLogin { it.disable() }
                .cors(Customizer.withDefaults())
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

                .authorizeHttpRequests { authorize ->
                    authorize
                            .anyRequest().permitAll()
                }

                .addFilterBefore(ExceptionFilter(objectMapper), AuthorizationFilter::class.java)
                .addFilterBefore(JwtTokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
