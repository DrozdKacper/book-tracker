package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.UserBookRequestDto;
import kacper.book_tracker.dto.UserBookResponseDto;
import kacper.book_tracker.entity.UserBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserBookMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserBookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserBookResponseDto toDto(UserBook userBook) {
        return modelMapper.map(userBook, UserBookResponseDto.class);
    }

    public UserBook toEntity(UserBookRequestDto userBookRequestDto) {
        return modelMapper.map(userBookRequestDto, UserBook.class);
    }



}
