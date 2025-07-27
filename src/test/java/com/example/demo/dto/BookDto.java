package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class BookDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
