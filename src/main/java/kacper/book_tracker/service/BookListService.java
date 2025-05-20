package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookListRequestDto;
import kacper.book_tracker.dto.BookListResponseDto;
import kacper.book_tracker.entity.BookList;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.mapper.BookListMapper;
import kacper.book_tracker.repository.BookListRepository;
import kacper.book_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookListService {
    private final BookListRepository bookListRepository;
    private final BookListMapper bookListMapper;
    private final UserService userService;

    @Autowired
    public BookListService(BookListRepository bookListRepository, BookListMapper bookListMapper,
                           UserService userService, UserRepository userRepository) {
        this.bookListRepository = bookListRepository;
        this.bookListMapper = bookListMapper;
        this.userService = userService;
    }

    public List<BookListResponseDto> getUserBookLists() {
        User user = userService.getCurrentUser();
        List<BookList> bookLists = bookListRepository.findAllByUser(user);
        return bookListMapper.toDto(bookLists);
    }

    public BookListResponseDto createBookList(BookListRequestDto dto) {
        User user = userService.getCurrentUser();
        BookList bookList = bookListMapper.toEntity(dto, user);
        BookList saved = bookListRepository.save(bookList);
        return bookListMapper.toDto(saved);
    }

    public BookListRequestDto updateBookList(BookListRequestDto updatedDto, int id) {
        User user = userService.getCurrentUser();
        BookList bookList = bookListRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("Book list not found"));

        bookList.setName(updatedDto.getName());
        bookList.setDescription(updatedDto.getDescription());

        BookList saved = bookListRepository.save(bookList);
        return bookListMapper.toRequestDto(saved);
    }

    public BookListResponseDto getBookList(int id) {
        User user = userService.getCurrentUser();

        BookList bookList = bookListRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("Book list not found"));

        return bookListMapper.toDto(bookList);
    }

        public String deleteList(int id) {
        User user = userService.getCurrentUser();

        BookList bookList = bookListRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("Book list not found"));

        bookListRepository.delete(bookList);

        return "Deleted List " + bookList.getName();
    }
}