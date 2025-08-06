package kacper.book_tracker.controller;

import jakarta.validation.Valid;
import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.service.AdminBookService;
import kacper.book_tracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/books")
public class AdminBookController {

    private final BookService bookService;
    private final AdminBookService adminBookService;

    @Autowired
    public AdminBookController(BookService bookService, AdminBookService adminBookService) {
        this.bookService = bookService;
        this.adminBookService = adminBookService;
    }

    /*
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooksForAdmin() {
        return ResponseEntity.ok(bookService.getBooks());
    }
    */
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminBookService.addBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        return ResponseEntity.ok(adminBookService.deleteBook(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable int id) {
        return ResponseEntity.ok(adminBookService.updateBook(bookDto, id));
    }
}
