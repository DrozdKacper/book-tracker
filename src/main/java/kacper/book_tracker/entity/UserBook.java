package kacper.book_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name ="list_id")
    private BookList bookList;
    private Integer rating;
    private Integer currentPage;
    private String notes;
    private Date addedAt;
    private Boolean finished;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
