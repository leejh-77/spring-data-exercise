package app

import app.jpa.BookRepository
import app.jpa.BookService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val testEnv: TestEnv
) {

    @BeforeAll
    fun beforeAll() {
        this.testEnv.createTestData()
    }

    @Test
    fun `test transaction requires new`() {
        try {
            this.bookService.removeAllBooks()
        } catch (e: Error) {
            println("Error occured")
        }
        println(this.bookRepository.count())
    }
}
