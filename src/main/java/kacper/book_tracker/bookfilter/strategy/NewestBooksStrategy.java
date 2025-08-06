package kacper.book_tracker.bookfilter.strategy;

import kacper.book_tracker.dto.BookListItemDto;
import kacper.book_tracker.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class NewestBooksStrategy implements BookFilterStrategy{

    private final ReviewRepository reviewRepository;


    @Autowired
    public NewestBooksStrategy(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Page<BookListItemDto> filterBooks(Pageable pageable) {
        return reviewRepository.findAllOrderByPublicationDateDesc(pageable);
    }
}
