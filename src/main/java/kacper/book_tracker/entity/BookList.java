package kacper.book_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "list")
@Data
@NoArgsConstructor
public class BookList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String description;
    private Date createdAt;
}
