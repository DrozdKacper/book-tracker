package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.exception.BookNotFoundException;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();

        return bookMapper.toDtoList(books);
    }

    public BookDto getBook(int id) {

        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id +" not found")));
    }


}
