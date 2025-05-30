package kacper.book_tracker.service;

import kacper.book_tracker.dto.ReviewRequestDto;
import kacper.book_tracker.dto.ReviewResponseDto;
import kacper.book_tracker.entity.Review;
import kacper.book_tracker.entity.UserBook;
import kacper.book_tracker.mapper.ReviewMapper;
import kacper.book_tracker.repository.ReviewRepository;
import kacper.book_tracker.repository.UserBookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserBookRepository userBookRepository;

    public ReviewService(ReviewMapper reviewMapper, ReviewRepository reviewRepository, UserBookRepository userBookRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
        this.userBookRepository = userBookRepository;
    }

    public ReviewResponseDto addReview(ReviewRequestDto dto) {
        Review review = reviewMapper.toEntity(dto);
        UserBook userBook = userBookRepository.findById(dto.getUserBookId())
                .orElseThrow(() -> new RuntimeException("UserBook not found"));
        review.setUserBook(userBook);
        review.setCreatedAt(new Date());
        review.setUpdatedAt(new Date());
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewResponseDto> getReviewsByBook(int id) {
        return reviewMapper.toDtoList(reviewRepository.findAllByUserBook_Id(id));
    }

    public ReviewResponseDto getReview(int id) {
        return reviewMapper.toDto(reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found")));
    }

    public ReviewResponseDto changeReview(int id, ReviewRequestDto dto) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        BeanUtils.copyProperties(dto, existingReview, "id", "userBook", "createdAt");
        existingReview.setUpdatedAt(new Date());
        return reviewMapper.toDto(reviewRepository.save(existingReview));
    }

    public String deleteReview(int id) {
        reviewRepository.deleteById(id);
        return "Review deleted successfully";
    }
}
