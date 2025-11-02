package com.turkcell.book_service.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class UpdateBookRequest {

    @NotBlank
    private String title;

    @Min(1400)
    private int publicationYear;

    private List<CreateBookRequest.AuthorDto> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<CreateBookRequest.AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<CreateBookRequest.AuthorDto> authors) {
        this.authors = authors;
    }
}


