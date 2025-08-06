package kacper.book_tracker.specification;

import kacper.book_tracker.dto.BookFilter;
import kacper.book_tracker.entity.Book;
import kacper.book_tracker.util.FilterUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookSpecification{
    private Specification<Book> createSpecification(BookFilter input) {
        switch (input.getOperator()) {
            case EQUALS:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                FilterUtils.castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue())));
            case NOT_EQUALS:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                FilterUtils.castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue())));
            case GREATER_THAN:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) FilterUtils.castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue())));
            case LESS_THAN:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) FilterUtils.castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue())));
            case LIKE:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()),
                                "%" + input.getValue() + "%"));
            case IN:
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(FilterUtils.castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValues())));
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    public Specification<Book> createSpecificationFromFilters(List<BookFilter> filters) {
        Specification<Book> result = Specification.where(null);

        for (BookFilter filter : filters) {
            result = result.and(createSpecification(filter));
        }
        return result;
    }
}
