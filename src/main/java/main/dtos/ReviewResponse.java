package main.dtos;

public class ReviewResponse {

    private int rating;
    private String comment;

    public ReviewResponse(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
