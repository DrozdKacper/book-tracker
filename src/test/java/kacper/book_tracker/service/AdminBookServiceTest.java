package kacper.book_tracker.service;

import kacper.book_tracker.dto.BookDto;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.mapper.BookMapper;
import kacper.book_tracker.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminBookServiceTest {
    @Mock private BookMapper bookMapper;
    @Mock private BookRepository bookRepository;

    @InjectMocks AdminBookService adminBookService;



    @Test
    void addBookShouldAddBookSuccessfully() {
        // given
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("George Orwell");
        bookDto.setTitle("1984");

        Book bookEntity = new Book();
        bookEntity.setAuthor("George Orwell");
        bookEntity.setTitle("1984");

        when(bookMapper.toEntity(bookDto)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDto);

        // when
        BookDto addedBookDto = adminBookService.addBook(bookDto);

        // then
        assertNotNull(addedBookDto);
        assertEquals("George Orwell", addedBookDto.getAuthor());
        assertEquals("1984", addedBookDto.getTitle());
    }
}
