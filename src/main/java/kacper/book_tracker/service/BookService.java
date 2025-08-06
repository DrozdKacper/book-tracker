package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.dto.BookFilter;
import kacper.book_tracker.dto.BookListItemDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.exception.BookNotFoundException;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import kacper.book_tracker.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, ReviewRepository reviewRepository){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.reviewRepository = reviewRepository;
    }

   /* public Page<BookListItemDto> getBooks(Pageable pageable) {

        return reviewRepository.findAllWithAverageRating(pageable);

    }
    */
    public BookDto getBook(int id) {

        BookDto bookDto = bookMapper.toDto(
                bookRepository.findById(id)
                        .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"))
        );

        Double averageRating = reviewRepository.findAverageRatingForBook(id);
        bookDto.setAverageRating(averageRating != null ? averageRating : 0.0);

        return bookDto;
    }

    public List<BookDto> getBooksByFilter(Specification<Book> specification) {
        return bookMapper.toDtoList(bookRepository.findAll(specification));
    }


}
