package kacper.book_tracker.bookfilter.strategy;

import kacper.book_tracker.dto.BookListItemDto;
import kacper.book_tracker.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MostTrendingBooksStrategy implements BookFilterStrategy{

    private final ReviewRepository reviewRepository;

    @Autowired
    public MostTrendingBooksStrategy(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Page<BookListItemDto> filterBooks(Pageable pageable) {
        LocalDateTime last30days = LocalDateTime.now().minusDays(30);
        return reviewRepository.findMostTrending(last30days, pageable);
    }
}
