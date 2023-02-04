# Spring data 실습 프로젝트

## REQUIRES_NEW
@Transaction 로 선언된 함수 안에서 @Transactional(propagation = REQUIRES_NEW) 로 선언된 함수를 호출하면
새로운 커넥션이 생성되어 별도의 트랜잭션 안에서 디비 동작이 실행된다  
이때, REQUIRES_NEW 가 선언된 함수 안에서 에러가 발생하면 호출한 쪽에서 에러를 catch 하지 않는다면 위쪽 트랜잭션은 롤백된다
```kotlin
// BookSubService.java
@Transactional(propagation = Propagation.REQUIRES_NEW) // 새로운 트랜잭션 생성. 이 함수를 빠져나가는 순간 flush 됨
fun removeBook(book: Book) {
    this.repository.delete(book)
    if (counter++ == 5) {
        throw Error("throw error")
    }
}

// BookService.java
@Transactional
fun removeAllBooks() {
    val books = this.repository.findAll()
    this.repository.save(Book("new book", "new book for error test")) // 2. 롤백으로 인해 이 Book 은 저장되지 않는다
    books.forEach {
        bookSubService.removeBook(it) // 1. 이곳에서 중간에 에러가 나게되면
    }
    // 3. 에러가 발생하기 이전에 removeBook() 함수에 삭제된 book 은 그대로 적용된다
}
```
