package app.jpa

import app.model.Book
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class BookSubService(
    private val repository: BookRepository
) {
    private var counter = 0

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun removeBook(book: Book) {
        this.repository.delete(book)
        if (counter++ == 5) {
            throw Error("throw error")
        }
    }
}
