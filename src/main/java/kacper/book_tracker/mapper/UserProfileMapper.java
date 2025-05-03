package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.UserProfileDto;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserProfileDto userProfileDto) {
        return modelMapper.map(userProfileDto, User.class);
    }

    public  UserProfileDto toDto(User user) {
        return modelMapper.map(user, UserProfileDto.class);
    }
}
