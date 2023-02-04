package app.jpa

import app.model.Book
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class BookSubService(
    private val repository: BookRepository
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun removeBook(book: Book) {
        this.repository.delete(book)
    }
}
