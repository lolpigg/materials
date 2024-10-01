package com.example.librarystar.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    public User(String login, String password, String registrationDate, String avatarPath, Book lastBook, Integer lastBookPage, int role, String publisherName, Date passwordRestoreDate) {
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
        this.avatarPath = avatarPath;
        this.lastBook = lastBook;
        this.lastBookPage = lastBookPage;
        this.role = role;
        this.publisherName = publisherName;
        this.passwordRestoreDate = passwordRestoreDate;
    }
    private Integer id;
    private String login;
    private String password;
    @SerializedName("registration_date")
    private String registrationDate;
    @SerializedName("avatar_path")
    private String avatarPath;
    @SerializedName("last_book")
    private Book lastBook;
    @SerializedName("last_book_page")
    private Integer lastBookPage; // Integer for possible null value
    private int role;
    @SerializedName("publisher_name")
    private String publisherName;
    @SerializedName("password_restore_date")
    private Date passwordRestoreDate;

    public String getLogin() {
        return login;
    }
    public Integer getId(){return id;}

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Book getLastBook() {
        return lastBook;
    }

    public void setLastBook(Book lastBook) {
        this.lastBook = lastBook;
    }

    public Integer getLastBookPage() {
        return lastBookPage;
    }

    public void setLastBookPage(Integer lastBookPage) {
        this.lastBookPage = lastBookPage;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Date getPasswordRestoreDate() {
        return passwordRestoreDate;
    }

    public void setPasswordRestoreDate(Date passwordRestoreDate) {
        this.passwordRestoreDate = passwordRestoreDate;
    }
}
