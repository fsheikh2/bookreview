package main.services;

import main.dtos.ReviewRequest;
import main.models.Book;
import main.models.Review;
import main.repositories.BookRepository;
import main.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    public Review addReview(Long bookId, ReviewRequest reviewRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setBook(book);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());

        return reviewRepository.save(review);
    }
}
