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
    fun removeAllBooks() {
        val books = this.repository.findAll()
        this.repository.save(Book("new book", "new book for error test"))
        books.forEach {
            bookSubService.removeBook(it)
        }
    }
}
