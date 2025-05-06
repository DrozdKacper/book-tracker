package kacper.book_tracker.controller;

import kacper.book_tracker.dto.BookListRequestDto;
import kacper.book_tracker.dto.BookListResponseDto;
import kacper.book_tracker.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-lists")
public class BookListController {

    private final BookListService bookListService;

    @Autowired
    public BookListController(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @GetMapping
    public List<BookListResponseDto> getUserBookLists() {
        return bookListService.getUserBookLists();
    }

    @PostMapping
    public BookListResponseDto createBookList(@RequestBody BookListRequestDto dto) {
        return bookListService.createBookList(dto);
    }

    @PutMapping("/{id}")
    public BookListRequestDto updateBookList(@RequestBody BookListRequestDto dto, @PathVariable int id) {
        return bookListService.updateBookList(dto, id);
    }

    @GetMapping("/{id}")
    public BookListResponseDto getBookList(@PathVariable int id) {
        return bookListService.getBookList(id);
    }

    @DeleteMapping("/{id}")
    public String deleteList(@PathVariable int id) {
        return bookListService.deleteList(id);
    }
}