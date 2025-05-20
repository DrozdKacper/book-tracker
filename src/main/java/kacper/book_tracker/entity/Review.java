package kacper.book_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    @OneToOne
    @JoinColumn(name = "Book_user_id")
    private UserBook userBook;
    private String title;
    private Integer rating;
    private Date createdAt;
    private Date updatedAt;
}
