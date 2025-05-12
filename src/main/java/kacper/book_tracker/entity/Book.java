package kacper.book_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private String genre;
    private Integer pageCount;

    @ManyToOne
    @JoinColumn(name = "book_list_id")
    private BookList bookList;


}
