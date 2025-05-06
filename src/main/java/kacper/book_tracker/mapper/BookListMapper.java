package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.BookListRequestDto;
import kacper.book_tracker.dto.BookListResponseDto;
import kacper.book_tracker.entity.BookList;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookListMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public BookListMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookList toEntity(BookListRequestDto dto, User user) {
        BookList bookList = modelMapper.map(dto, BookList.class);
        bookList.setUser(user);
        return bookList;
    }

    public BookListResponseDto toDto(BookList bookList) {
        BookListResponseDto dto = modelMapper.map(bookList, BookListResponseDto.class);
        dto.setNumberOfBooks(bookList.getBooks().size());
        return dto;
    }

    public List<BookListResponseDto> toDto(List<BookList> bookLists) {
        return bookLists.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BookListRequestDto toRequestDto(BookList bookList) {
        return modelMapper.map(bookList, BookListRequestDto.class);
    }
}