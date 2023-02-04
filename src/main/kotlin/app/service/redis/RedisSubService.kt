package app.service.redis

import app.model.RedisItem
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RedisSubService(
    val redisRepository: RedisRepository
) {
    private var counter = 0

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun removeRedisItem(key: String) {
        this.redisRepository.delete(key)
        if (counter++ == 5) {
            throw Error("error")
        }
    }
}
