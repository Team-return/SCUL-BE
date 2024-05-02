package scul.projectscul.global.security.domain

import lombok.Builder
import lombok.Getter
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@Getter
@Builder
@RedisHash
class RefreshToken (
    @Id
    val id: String,

    @Indexed
    val token: String,

    @TimeToLive
    val ttl: Long
)