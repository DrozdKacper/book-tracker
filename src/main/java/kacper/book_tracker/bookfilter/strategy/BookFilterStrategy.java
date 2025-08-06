package kacper.book_tracker.bookfilter.strategy;

import kacper.book_tracker.dto.BookListItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface BookFilterStrategy {
    Page<BookListItemDto> filterBooks(Pageable pageable);
}
