package kacper.book_tracker.repository;

import kacper.book_tracker.dto.UserProfileDto;
import kacper.book_tracker.entity.BookList;
import kacper.book_tracker.entity.User;
import kacper.book_tracker.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookListRepository extends JpaRepository<BookList, Integer> {
    List<BookList> findAllByUser(User user);
    Optional<BookList> findByUserAndId(User user, int id);

}
