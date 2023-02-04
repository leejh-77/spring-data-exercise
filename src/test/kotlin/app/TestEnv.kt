package app

import app.service.jpa.BookRepository
import app.model.Book
import app.model.RedisItem
import app.service.redis.RedisRepository
import org.springframework.stereotype.Component

@Component
class TestEnv(
    private val bookRepository: BookRepository,
    private val redisRepository: RedisRepository
) {
    fun createTestBooks() {
        this.bookRepository.deleteAll()

        (1..10).forEach {
            val book = Book("algorithm - $it", "great algorithm book")
            this.bookRepository.save(book)
        }
    }

    fun createTestRedisItems() {
        this.redisRepository.deleteAll()

        (1..10).forEach {
            val item = RedisItem("item - $it")
            this.redisRepository.save(item)
        }
    }
}
