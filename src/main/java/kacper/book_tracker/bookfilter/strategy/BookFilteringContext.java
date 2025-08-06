package kacper.book_tracker.bookfilter.strategy;

import kacper.book_tracker.dto.BookListItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookFilteringContext {

    private BookFilterStrategy bookFilterStrategy;

    public void setBookFilterStrategy(BookFilterStrategy bookFilterStrategy) {
        this.bookFilterStrategy = bookFilterStrategy;
    }

    public Page<BookListItemDto> filterBooks(Pageable pageable) {
        return this.bookFilterStrategy.filterBooks(pageable);
    }
}
