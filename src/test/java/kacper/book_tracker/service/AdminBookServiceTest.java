package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.exception.BookAlreadyExistsException;
import kacper.book_tracker.exception.BookNotFoundException;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminBookServiceTest {
    @Mock private BookMapper bookMapper;
    @Mock private BookRepository bookRepository;

    @InjectMocks AdminBookService adminBookService;

    private BookDto bookDto;
    private Book bookEntity;

    @BeforeEach
    void init() {
        bookDto = new BookDto();
        bookDto.setAuthor("George Orwell");
        bookDto.setTitle("1984");
        bookDto.setIsbn("9780451524935");

        bookEntity = new Book();
        bookEntity.setAuthor("George Orwell");
        bookEntity.setTitle("1984");
        bookEntity.setIsbn("9780451524935");



    }


    @Test
    void addBookShouldAddBookSuccessfully() {
        // given
        when(bookMapper.toEntity(bookDto)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDto);


        // when
        BookDto addedBookDto = adminBookService.addBook(bookDto);

        // then
        assertNotNull(addedBookDto);
        assertEquals("George Orwell", addedBookDto.getAuthor());
        assertEquals("1984", addedBookDto.getTitle());
        assertEquals("9780451524935", addedBookDto.getIsbn());
        
    }

    @Test
    void shouldThrowBookAlreadyExistsExceptionWhenBookAlreadyExists() {
        when(bookMapper.toEntity(bookDto)).thenReturn(bookEntity);



        when(bookRepository.save(bookEntity)).thenThrow(new DataIntegrityViolationException("Duplicate"));
        BookAlreadyExistsException exception = assertThrows(BookAlreadyExistsException.class, () -> {
            adminBookService.addBook(bookDto);
        });

        assertEquals("Book already exists George Orwell 1984", exception.getMessage());


    }

    @Test
    void updateBookShouldUpdateBookSuccessfully() {
        // given
        BookDto updatedDto = new BookDto();
        updatedDto.setAuthor("Aldous Huxley");
        updatedDto.setTitle("Brave New World");
        updatedDto.setIsbn("9780060850524");

        Book updatedEntity = new Book();
        updatedEntity.setAuthor("Aldous Huxley");
        updatedEntity.setTitle("Brave New World");
        updatedEntity.setIsbn("9780060850524");


        when(bookRepository.findById(1)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(updatedDto);


        BookDto result = adminBookService.updateBook(updatedDto, 1);


        assertNotNull(result);
        assertEquals("Aldous Huxley", result.getAuthor());
        assertEquals("Brave New World", result.getTitle());
        assertEquals("9780060850524", result.getIsbn());
    }

    @Test
    void updateBookShouldThrowBookNotFoundExceptionWhenBookNotFound() {
       when(bookRepository.findById(2)).thenReturn(Optional.empty());

       BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
           adminBookService.updateBook(bookDto, 2);
       });

       assertEquals("Book with id 2 not found", exception.getMessage());

    }

    @Test
    void deleteBookShouldDeleteBookSuccessfully() {
        when(bookRepository.existsById(1)).thenReturn(true);

        String result = adminBookService.deleteBook(1);

        assertEquals("Book deleted successfully: " + 1, result);
        verify(bookRepository).deleteById(1);

    }

    @Test
    void deleteBookShouldThrowBookNotFoundExceptionWhenBookNotFound() {
        when(bookRepository.existsById(100)).thenReturn(false);

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () ->{
            adminBookService.deleteBook(100);
        });
        assertEquals("Book with id 100 not found", exception.getMessage());
    }


}
