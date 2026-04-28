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

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getAverageRating() { return averageRating; }
    public List<ReviewResponse> getReviews() { return reviews; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
    public void setReviews(List<ReviewResponse> reviews) { this.reviews = reviews; }

}
