package kacper.book_tracker.service;

import kacper.book_tracker.dto.UserBookRequestDto;
import kacper.book_tracker.dto.UserBookResponseDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.entity.BookList;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.entity.UserBook;
import kacper.book_tracker.exception.BookListNotFoundException;
import kacper.book_tracker.exception.BookNotFoundException;
import kacper.book_tracker.exception.UserBookNotFoundException;
import kacper.book_tracker.mapper.UserBookMapper;
import kacper.book_tracker.repository.BookListRepository;
import kacper.book_tracker.repository.BookRepository;
import kacper.book_tracker.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {

    private final BookListRepository bookListRepository;
    private final UserService userService;
    private final UserBookRepository userBookRepository;
    private final UserBookMapper userBookMapper;
    private final BookRepository bookRepository;


    @Autowired
    public UserBookService(BookListRepository bookListRepository, UserService userService,
                           UserBookRepository userBookRepository,
                           UserBookMapper userBookMapper,
                           BookRepository bookRepository) {
        this.bookListRepository = bookListRepository;
        this.userService = userService;
        this.userBookRepository = userBookRepository;
        this.userBookMapper = userBookMapper;
        this.bookRepository = bookRepository;
    }

    public UserBookResponseDto addBookToUserList(UserBookRequestDto userBookRequestDto) {
        User user = userService.getCurrentUser();

        Book book = bookRepository.findById(userBookRequestDto.getBookId()).
                orElseThrow(() -> new BookNotFoundException("Book with id: " + userBookRequestDto.getBookId() + " not found"));

        BookList bookList = bookListRepository.findByUserAndId(user, userBookRequestDto.getBookListId())
                .orElseThrow(() -> new BookListNotFoundException("List with id: " + userBookRequestDto.getBookListId() + " not found"));

        UserBook userBook = userBookMapper.toEntity(userBookRequestDto);

        userBook.setBookList(bookList);
        userBook.setUser(user);
        userBook.setBook(book);

        userBookRepository.save(userBook);

        return userBookMapper.toDto(userBook);


    }

    public String deleteBookFromUserList(int bookId, int bookListId) {

        User user = userService.getCurrentUser();

       UserBook userBook =  userBookRepository.findByUserAndBookIdAndBookListId(user,
               bookId, bookListId)
               .orElseThrow(() -> new UserBookNotFoundException("User book not found"));

       userBookRepository.delete(userBook);

        return "Book deleted successfully from the list";


    }





}
