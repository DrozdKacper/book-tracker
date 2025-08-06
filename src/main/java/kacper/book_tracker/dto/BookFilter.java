package kacper.book_tracker.dto;

import lombok.Data;

import java.util.List;
@Data
public class BookFilter {
    private String field;
    private QueryOperator operator;
    String value;
    List<String> values;
}
