package com.example.librarystar.Models;

public class Book {
    private int id;

    public Book(String name, int year_of_creating, String image_path, String pdf_path, int publisher, int genre, boolean is_deleted, String delete_text, boolean is_available, String description) {
        this.name = name;
        this.description = description;
        this.year_of_creating = year_of_creating;
        this.image_path = image_path;
        this.pdf_path = pdf_path;
        this.publisher = publisher;
        this.genre = genre;
        this.is_deleted = is_deleted;
        this.delete_text = delete_text;
        this.is_available = is_available;
    }

    private String name;
    private String description;
    private int year_of_creating;
    private String image_path;
    private String pdf_path;
    private int publisher;
    private int genre;
    private boolean is_deleted;
    private String delete_text;
    private boolean is_available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_creating() {
        return year_of_creating;
    }

    public void setYear_of_creating(int year_of_creating) {
        this.year_of_creating = year_of_creating;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPdf_path() {
        return pdf_path;
    }

    public void setPdf_path(String pdf_path) {
        this.pdf_path = pdf_path;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getDelete_text() {
        return delete_text;
    }

    public void setDelete_text(String delete_text) {
        this.delete_text = delete_text;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
