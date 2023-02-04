package app.jpa

import app.model.Book
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val repository: BookRepository,
    private val bookSubService: BookSubService
){

    @Transactional
    fun saveBook(book: Book) {
        val books = this.repository.findAll()
        books.forEach {
            bookSubService.removeBook(it)
        }
    }
}
