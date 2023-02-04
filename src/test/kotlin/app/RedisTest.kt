package app

import app.service.redis.RedisService
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisTest {

    @Autowired
    lateinit var testEnv: TestEnv

    @Autowired
    lateinit var redisService: RedisService

    @BeforeAll
    fun beforeAll() {
        this.testEnv.createTestRedisItems()
    }

    @Test
    fun `test redis transaction requires new`() {
        try {
            this.redisService.removeAll()
        } catch (e: Error) {
            println("Error caught")
        }
    }
}
