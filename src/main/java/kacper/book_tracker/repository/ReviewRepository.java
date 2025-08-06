package kacper.book_tracker.repository;

import kacper.book_tracker.dto.BookListItemDto;
import kacper.book_tracker.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByUserBook_Id(int userBookId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageRatingForBook(@Param("bookId") int bookId);

    @Query("""
            SELECT new kacper.book_tracker.dto.BookListItemDto(
                b.id, b.title, b.author, b.genre, AVG(r.rating), COUNT(r.id)
            )
            FROM Book b
            LEFT JOIN b.reviews r
            GROUP BY b.id, b.title, b.author, b.genre
            ORDER BY COUNT(r.id) DESC
    """)
    Page<BookListItemDto> findAllOrderByReviewCountDesc(Pageable pageable);

    @Query("""
            SELECT new kacper.book_tracker.dto.BookListItemDto(
                b.id, b.title, b.author, b.genre, AVG(r.rating), COUNT(r.id)
            )
            FROM Book b
            LEFT JOIN b.reviews r
            GROUP BY b.id, b.title, b.author, b.genre
            ORDER BY AVG(r.rating) DESC
    """)
    Page<BookListItemDto> findAllOrderByAverageRatingDesc(Pageable pageable);

    @Query("""
    SELECT new kacper.book_tracker.dto.BookListItemDto(
        b.id, b.title, b.author, b.genre, AVG(r.rating), COUNT(r.id)
    )
    FROM Book b
    LEFT JOIN b.reviews r
    
    GROUP BY b.id, b.title, b.author, b.genre
    ORDER BY COUNT(r.id) DESC
    """)
    Page<BookListItemDto> findMostTrending(Pageable pageable);

    @Query("""
            SELECT new kacper.book_tracker.dto.BookListItemDto(
                b.id, b.title, b.author, b.genre, AVG(r.rating), COUNT(r.id)
            )
            FROM Book b
            LEFT JOIN b.reviews r
            GROUP BY b.id, b.title, b.author, b.genre
            ORDER BY b.publicationDate DESC
    """)
    Page<BookListItemDto> findAllOrderByPublicationDateDesc(Pageable pageable);


}
