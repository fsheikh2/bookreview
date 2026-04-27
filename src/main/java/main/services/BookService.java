package main.services;

import main.dtos.BookRequest;
import main.models.Book;
import main.models.Review;
import main.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());

        return bookRepository.save(book);
    }

    public double getAverageRating(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new RuntimeException("Book not found"));

        return book.getReviews()
                .stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
