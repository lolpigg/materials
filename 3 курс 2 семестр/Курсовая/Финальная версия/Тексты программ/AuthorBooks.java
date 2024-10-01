package com.example.librarystar.Models;

public class AuthorBooks {
    private int id;
    public AuthorBooks(int author, int book) {
        this.author = author;
        this.book = book;
    }

    private int author;
    private int book;

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }
}
