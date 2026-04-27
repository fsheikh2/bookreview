package main.dtos;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;
}
