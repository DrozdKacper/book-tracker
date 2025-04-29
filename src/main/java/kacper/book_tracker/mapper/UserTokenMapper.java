package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.UserTokenDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTokenMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserTokenMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
