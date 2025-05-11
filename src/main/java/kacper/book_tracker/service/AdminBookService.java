package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public AdminBookService(BookMapper bookMapper, BookRepository bookRepository, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.save(bookMapper.toEntity(bookDto));
    }

    public BookDto updateBook(BookDto updatedBook, int id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BeanUtils.copyProperties(updatedBook, existingBook, "id");
        bookRepository.save(existingBook);
        return bookMapper.toDto(existingBook);
    }

    public String deleteBook(int id) {
        bookRepository.deleteById(id);
        return "User deleted successfully" + id;
    }

}
