package kacper.book_tracker.mapper;

import kacper.book_tracker.dto.ReviewRequestDto;
import kacper.book_tracker.dto.ReviewResponseDto;
import kacper.book_tracker.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Review toEntity(ReviewRequestDto dto) {
        return modelMapper.map(dto, Review.class);
    }

    public ReviewResponseDto toDto(Review review) {
        ReviewResponseDto dto = modelMapper.map(review, ReviewResponseDto.class);
        dto.setBookTitle(review.getUserBook().getBook().getTitle());
        dto.setUsername(review.getUserBook().getUser().getUsername());
        return dto;
    }


    public List<ReviewResponseDto> toDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
