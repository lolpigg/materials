package com.example.librarystar.Models;

public class Notification {
    private int id;

    public Notification(String description, int action, int book, boolean isAccepted, String discardText) {
        this.description = description;
        this.action = action;
        this.book = book;
        this.is_accepted = isAccepted;
        this.discard_text = discardText;
    }

    private String description;
    private int action;
    private int book;
    private boolean is_accepted;
    private String discard_text;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public boolean isAccepted() {
        return is_accepted;
    }

    public void setAccepted(boolean accepted) {
        is_accepted = accepted;
    }

    public String getDiscardText() {
        return discard_text;
    }

    public void setDiscardText(String discardText) {
        this.discard_text = discardText;
    }

    public int getId() {
        return id;
    }
}
