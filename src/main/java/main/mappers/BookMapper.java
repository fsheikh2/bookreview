package main.mappers;

import main.dtos.BookResponse;
import main.dtos.ReviewResponse;
import main.models.Book;
import main.models.Review;

import java.util.List;

public class BookMapper {

    public static BookResponse mapToBookResponse(Book book) {

        List<ReviewResponse> reviews = book.getReviews()
                .stream()
                .map(r -> new ReviewResponse(r.getRating(), r.getComment()))
                .toList();

        double avg = book.getReviews()
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                avg,
                reviews
        );
    }
}
