package com.example.librarystar.Models;

public class Author {
    private String full_name;
    private int id;
    private int year_of_birth;
    private String image_path;
    private Integer year_of_death; // Integer for possible null value
    //private String literaryDirection;
    public Author(String full_name, int year_of_birth, Integer year_of_death, String image_path) {
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
        this.year_of_death = year_of_death;
        this.image_path = image_path;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public Integer getYear_of_death() {
        return year_of_death;
    }

    public void setYear_of_death(Integer year_of_death) {
        this.year_of_death = year_of_death;
    }

    //public String getLiteraryDirection() {
    //    return literaryDirection;
    //}
    //public void setLiteraryDirection(String literaryDirection) {
    //    this.literaryDirection = literaryDirection;
    //}

    public int getId() {
        return id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
