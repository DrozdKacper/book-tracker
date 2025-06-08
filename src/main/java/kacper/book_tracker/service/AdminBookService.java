package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.exception.BookAlreadyExistsException;
import kacper.book_tracker.exception.BookNotFoundException;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminBookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public AdminBookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookDto bookDto) {

        try {
            Book book = bookRepository.save(bookMapper.toEntity(bookDto));
            return bookMapper.toDto(book);
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistsException("Book already exists " + bookDto.getAuthor() + " " + bookDto.getTitle());
        }
    }

    public BookDto updateBook(BookDto updatedBook, int id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));

        BeanUtils.copyProperties(updatedBook, existingBook, "id");
        bookRepository.save(existingBook);
        return bookMapper.toDto(existingBook);
    }

    public String deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted successfully: " + id;
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }

    }

}
