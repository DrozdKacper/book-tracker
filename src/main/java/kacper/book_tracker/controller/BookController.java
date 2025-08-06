package kacper.book_tracker.controller;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.dto.BookFilter;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.service.BookService;
import kacper.book_tracker.specification.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookSpecification bookSpecification;

    @Autowired
    public BookController(BookService bookService, BookSpecification bookSpecification) {
        this.bookService = bookService;
        this.bookSpecification = bookSpecification;
    }
/*
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

 */
  @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<BookDto>> getBooksByFilters(@RequestBody List<BookFilter> filters) {
        Specification<Book> spec = bookSpecification.createSpecificationFromFilters(filters);
        List<BookDto> books = bookService.getBooksByFilter(spec);
        return ResponseEntity.ok(books);
    }
}
