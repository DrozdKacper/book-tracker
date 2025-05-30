package kacper.book_tracker.controller;

import kacper.book_tracker.dto.UserBookRequestDto;
import kacper.book_tracker.dto.UserBookResponseDto;
import kacper.book_tracker.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/userBooks")
public class UserBookController {

    private final UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping
    public ResponseEntity<UserBookResponseDto> addBookToUserList(@RequestBody UserBookRequestDto userBookRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userBookService.addBookToUserList(userBookRequestDto));
    }
    @DeleteMapping("/{bookId}/{bookListId}")
    public ResponseEntity<String> deleteBookFromUserList(@PathVariable int bookId, @PathVariable int bookListId) {
        return ResponseEntity.ok(userBookService.deleteBookFromUserList(bookId, bookListId));
    }


}
