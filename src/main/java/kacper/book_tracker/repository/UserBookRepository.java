package kacper.book_tracker.repository;

import kacper.book_tracker.entity.User;
import kacper.book_tracker.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Integer> {
    Optional<UserBook> findByUserAndBookIdAndBookListId(User user, int bookId, int bookListId);
}
