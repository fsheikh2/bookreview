package main.controllers;

import jakarta.validation.Valid;
import main.dtos.BookRequest;
import main.dtos.BookResponse;
import main.dtos.ReviewRequest;
import main.dtos.ReviewResponse;
import main.mappers.BookMapper;
import main.models.Book;
import main.models.Review;
import main.services.BookService;
import main.services.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    public BookController(BookService bookService, ReviewService reviewService) {

        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        Book book = bookService.createBook(bookRequest);
        return BookMapper.mapToBookResponse(book);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return BookMapper.mapToBookResponse(book);
    }

    @PostMapping("/{id}/reviews")
    public ReviewResponse addReview(@PathVariable("id") Long id, @RequestBody @Valid ReviewRequest reviewRequest) {
        Review review = reviewService.addReview(id, reviewRequest);

        return new ReviewResponse(review.getRating(), review.getComment());
    }
}
