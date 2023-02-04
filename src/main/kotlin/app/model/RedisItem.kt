package app.model

import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("item", timeToLive = 30)
class RedisItem(
    name: String
) {
    val id: String = UUID.randomUUID().toString()
    var name: String? = name; private set
}
