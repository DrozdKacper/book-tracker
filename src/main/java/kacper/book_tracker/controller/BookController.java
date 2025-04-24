package kacper.book_tracker.controller;

import kacper.book_tracker.dto.BookDTO;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/api/books")
@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
