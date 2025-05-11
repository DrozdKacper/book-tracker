package kacper.book_tracker.controller;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.service.AdminBookService;
import kacper.book_tracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/books")
public class AdminBookController {

    private final BookService bookService;
    private final AdminBookService adminBookService;

    @Autowired
    public AdminBookController(BookService bookService, AdminBookService adminBookService) {
        this.bookService = bookService;
        this.adminBookService = adminBookService;
    }


    @GetMapping
    public List<BookDto> getAllBooksForAdmin() {
        return bookService.getBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto) {
        return adminBookService.addBook(bookDto);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return adminBookService.deleteBook(id);
    }
    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable int id) {
        return adminBookService.updateBook(bookDto, id);
    }
}
