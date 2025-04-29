package kacper.book_tracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegisterUserDto {

    private String username;
    private String email;
    private String password;
}
