package app.service.redis

import app.model.RedisItem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RedisService(
    private val redisRepository: RedisRepository,
    private val redisSubService: RedisSubService
) {

    @Transactional
    fun removeAll() {
        val cursor = this.redisRepository.findAll()
        this.redisRepository.save(RedisItem("new redis item"))
        cursor.forEach {
            this.perform(it, false)
        }
    }

    private fun perform(key: String, catchException: Boolean = true) {
        if (catchException) {
            try {
                this.redisSubService.removeRedisItem(key)
            } catch (e: Error) {
                println("Rollback occurred for $key")
            }
        } else {
            this.redisSubService.removeRedisItem(key)
        }
    }
}
