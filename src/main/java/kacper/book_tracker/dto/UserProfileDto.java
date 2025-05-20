package kacper.book_tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserProfileDto {

    private String username;
    private String email;
    private Date createdAt;
}
