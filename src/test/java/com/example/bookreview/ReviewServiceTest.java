package com.example.bookreview;

import main.dtos.ReviewRequest;
import main.models.Book;
import main.models.Review;
import main.repositories.BookRepository;
import main.repositories.ReviewRepository;
import main.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void shouldAddReviewToBook(){
        // Setup
        Long bookId = 1L;

        Book book = new Book();
        book.setId(bookId);

        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setRating(5);
        reviewRequest.setComment("Loved it, my new fav book!");

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.of(book));

        when(reviewRepository.save(any(Review.class) ) )
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Review result = reviewService.addReview(bookId, reviewRequest);

        // Check
        assertEquals(5, result.getRating());
        assertEquals("Loved it, my new fav book!", result.getComment());
        assertEquals(book, result.getBook());
    }

    @Test
    void shouldThrowErrorWhenBookNotFound(){
        Long bookId = 1L;

        when(bookRepository.findById(bookId))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reviewService.addReview(bookId, new ReviewRequest()));
    }
}
