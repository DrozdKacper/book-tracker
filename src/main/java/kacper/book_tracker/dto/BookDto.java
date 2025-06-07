package kacper.book_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto {
    private int id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    private String description;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private String genre;
    private Integer pageCount;


}
