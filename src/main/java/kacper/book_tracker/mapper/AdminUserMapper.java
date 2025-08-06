package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.UserAdminViewDto;
import kacper.book_tracker.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminUserMapper {
    private final ModelMapper modelMapper;


    @Autowired
    public AdminUserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserAdminViewDto toDto(User user) {
        return modelMapper.map(user, UserAdminViewDto.class);
    }

    public List<UserAdminViewDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<UserAdminViewDto> toDtoPage(Page<User> users) {
        return users.map(this::toDto);
    }
}
