package main.dtos;

public class ReviewResponse {

    private int rating;
    private String comment;

    public ReviewResponse(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() { return rating; }
    public String getComment() { return comment; }

    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
}
