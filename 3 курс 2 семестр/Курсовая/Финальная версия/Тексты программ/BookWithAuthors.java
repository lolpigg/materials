package com.example.librarystar.Models;

import java.util.ArrayList;
import java.util.List;

public class BookWithAuthors {
    public BookWithAuthors(Book b, ArrayList<Author> a){
        book = b;
        authors = a;
    }
    private Book book;
    private ArrayList<Author> authors;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
