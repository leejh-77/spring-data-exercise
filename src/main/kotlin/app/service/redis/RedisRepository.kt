package app.service.redis

import app.model.RedisItem
import org.springframework.data.redis.core.Cursor
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ScanOptions
import org.springframework.stereotype.Component

@Component
class RedisRepository(
    val redisTemplate: RedisTemplate<String, RedisItem>
) {

    fun findAll(): Cursor<String> {
        return this.redisTemplate.scan(ScanOptions.NONE)
    }

    fun delete(key: String) {
        this.redisTemplate.delete(key)
    }

    fun save(item: RedisItem) {
        this.redisTemplate.opsForValue().set(item.id, item)
    }

    fun deleteAll() {
        this.findAll().forEach { delete(it) }
    }
}
