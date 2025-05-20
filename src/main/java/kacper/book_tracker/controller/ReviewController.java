package kacper.book_tracker.controller;

import kacper.book_tracker.dto.ReviewRequestDto;
import kacper.book_tracker.dto.ReviewResponseDto;
import kacper.book_tracker.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ReviewResponseDto addReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.addReview(reviewRequestDto);
    }

    @GetMapping("/book/{id}")
    public List<ReviewResponseDto> getReviewsByBook(@PathVariable int id) {
        return reviewService.getReviewsByBook(id);
    }

    @GetMapping("/{id}")
    public ReviewResponseDto getReview(@PathVariable int id) {
        return reviewService.getReview(id);
    }

    @PutMapping("/{id}")
    public ReviewResponseDto changeReview(@PathVariable int id, @RequestBody ReviewRequestDto reviewRequestDto) {
        return reviewService.changeReview(id, reviewRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable int id) {
        return reviewService.deleteReview(id);
    }
}