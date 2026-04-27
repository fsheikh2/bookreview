package main.controllers;

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

    public Review addReview(Long bookId, int rating, String comment) {
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setBook(book);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }
}
