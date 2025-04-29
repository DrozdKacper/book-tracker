package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.RegisterUserDto;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterUserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

   public User toEntity(RegisterUserDto registerUserDto) {
        return modelMapper.map(registerUserDto, User.class);
   }
}
