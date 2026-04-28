package com.example.bookreview;

import main.dtos.BookRequest;
import main.models.Book;
import main.models.Review;
import main.repositories.BookRepository;
import main.repositories.ReviewRepository;
import main.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    BookService bookService;

    private static final String LOTR_TITLE = "LORD OF THE RINGS";

    private static final String LOTR_AUTHOR = "J.R. Tolkein";

    private static final Long LOTR_ID = 1L;

    @Test
    void addsNewBookSuccessfully(){

        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle(LOTR_TITLE);
        bookRequest.setAuthor(LOTR_AUTHOR);

        Book savedBook = new Book();
        savedBook.setId(LOTR_ID);
        savedBook.setTitle(LOTR_TITLE);
        savedBook.setAuthor(LOTR_AUTHOR);

        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);

        Book resultBook = bookService.createBook(bookRequest);

        assertEquals(resultBook.getTitle(), LOTR_TITLE);
        assertEquals(resultBook.getAuthor(), LOTR_AUTHOR);
    }

    @Test
    void getsBookSuccessfullyById(){

        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle(LOTR_TITLE);
        bookRequest.setAuthor(LOTR_AUTHOR);

        Book lordOfTheRings = new Book();
        lordOfTheRings.setId(LOTR_ID);
        lordOfTheRings.setTitle(LOTR_TITLE);
        lordOfTheRings.setAuthor(LOTR_AUTHOR);

        when(bookRepository.findById(LOTR_ID))
                .thenReturn(Optional.of( lordOfTheRings ));

        Book resultBook = bookService.getBookById(LOTR_ID);

        assertEquals(resultBook.getTitle(), LOTR_TITLE);
        assertEquals(resultBook.getAuthor(), LOTR_AUTHOR);
    }

    @Test
    void throwsExceptionWhenBookNotFound(){
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle(LOTR_TITLE);
        bookRequest.setAuthor(LOTR_AUTHOR);

        when(bookRepository.findById(LOTR_ID)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> bookService.getBookById(LOTR_ID));

        assertEquals("Book not found", exception.getMessage());

    }

    @Test
    void getAverageRatingSuccessfully(){
        Book lordOfTheRings = new Book();
        lordOfTheRings.setId(LOTR_ID);
        lordOfTheRings.setTitle(LOTR_TITLE);
        lordOfTheRings.setAuthor(LOTR_AUTHOR);

        Review review1 = new Review();
        review1.setRating(5);
        Review review2 = new Review();
        review2.setRating(3);
        Review review3 = new Review();
        review3.setRating(4);

        List<Review> reviews = List.of(review1, review2, review3);
        lordOfTheRings.setReviews(reviews);

        when(bookRepository.findById(LOTR_ID))
                .thenReturn(Optional.of( lordOfTheRings ));

        double averageRatingFromService = bookService.getAverageRating(LOTR_ID);
        double expectedAverageRating = (review1.getRating()+ review2.getRating()+review3.getRating()) / reviews.size();

        assertEquals(expectedAverageRating, averageRatingFromService);
    }

    @Test
    void averageRatingIsZeroIfNoRatings(){
        Book lordOfTheRings = new Book();
        lordOfTheRings.setId(LOTR_ID);
        lordOfTheRings.setTitle(LOTR_TITLE);
        lordOfTheRings.setAuthor(LOTR_AUTHOR);

        when(bookRepository.findById(LOTR_ID))
                .thenReturn(Optional.of( lordOfTheRings ));

        double averageRatingFromService = bookService.getAverageRating(LOTR_ID);
        assertEquals(0, averageRatingFromService);
    }
}
