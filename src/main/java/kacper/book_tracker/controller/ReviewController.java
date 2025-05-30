package kacper.book_tracker.controller;

import kacper.book_tracker.dto.ReviewRequestDto;
import kacper.book_tracker.dto.ReviewResponseDto;
import kacper.book_tracker.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewRequestDto));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByBook(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.getReviewsByBook(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> changeReview(@PathVariable int id, @RequestBody ReviewRequestDto reviewRequestDto) {
        return ResponseEntity.ok(reviewService.changeReview(id, reviewRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}