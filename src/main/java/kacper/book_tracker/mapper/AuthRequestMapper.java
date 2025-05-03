package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.AuthRequestDto;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestMapper {
    private final ModelMapper modelMapper;

    public AuthRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(AuthRequestDto authRequestDto) {

        return modelMapper.map(authRequestDto, User.class);

    }

}