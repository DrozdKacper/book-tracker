package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.UpdateUserProfileDto;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserProfileDtoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateUserProfileDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UpdateUserProfileDto toDto(User user) {
        return modelMapper.map(user, UpdateUserProfileDto.class);
    }

    public User toEntity(UpdateUserProfileDto dto) {
        return modelMapper.map(dto, User.class);
    }



}
