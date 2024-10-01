package com.example.librarystar.Models;

public class UserBooks {
    private int id;

    public UserBooks(int book, int user) {
        this.book = book;
        this.user = user;
        is_active = true;
    }

    private int book;
    private boolean is_active;
    private int user;

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
