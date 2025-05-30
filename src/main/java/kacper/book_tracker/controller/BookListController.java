package kacper.book_tracker.controller;

import kacper.book_tracker.dto.BookListRequestDto;
import kacper.book_tracker.dto.BookListResponseDto;
import kacper.book_tracker.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-lists")
public class BookListController {

    private final BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @GetMapping
    public ResponseEntity<List<BookListResponseDto>> getUserBookLists() {
        return ResponseEntity.ok(bookListService.getUserBookLists());
    }

    @PostMapping
    public ResponseEntity<BookListResponseDto> createBookList(@RequestBody BookListRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookListService.createBookList(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookListRequestDto> updateBookList(@RequestBody BookListRequestDto dto, @PathVariable int id) {
        return ResponseEntity.ok(bookListService.updateBookList(dto, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookListResponseDto> getBookList(@PathVariable int id) {
        return ResponseEntity.ok(bookListService.getBookList(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteList(@PathVariable int id) {
        return ResponseEntity.ok(bookListService.deleteList(id));
    }
}