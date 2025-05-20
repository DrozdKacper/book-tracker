package kacper.book_tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserAdminViewDto {
    private int id;
    private String username;
    private String email;
    private String role;
    private Date createdAt;
}
