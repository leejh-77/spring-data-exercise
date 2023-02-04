package app

import app.jpa.BookRepository
import app.model.Book
import org.springframework.stereotype.Component

@Component
class TestEnv(
    private val bookRepository: BookRepository
) {
    fun createTestData() {
        this.bookRepository.deleteAll()

        (1..10).forEach {
            val book = Book("algorithm - $it", "great algorithm book")
            this.bookRepository.save(book)
        }
    }
}
