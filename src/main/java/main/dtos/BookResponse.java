package main.dtos;

import java.util.List;

public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private double averageRating;
    private List<ReviewResponse> reviews;

    public BookResponse(Long id, String title, String author, double averageRating, List<ReviewResponse> reviews) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.averageRating = averageRating;
        this.reviews = reviews;
    }
}
