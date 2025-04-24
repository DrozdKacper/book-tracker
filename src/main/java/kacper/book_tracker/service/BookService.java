package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDTO;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;

    }

    public List<BookDTO> getAllBooks(){

        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }
}
