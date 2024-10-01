package com.example.librarystar.Models;

public class Genre {
    private int id;

    public Genre(String name, String icon_path, String first_color, String second_color) {
        this.name = name;
        this.icon_path = icon_path;
        this.first_color = first_color;
        this.second_color = second_color;
    }

    private String name;
    private String icon_path;
    private String first_color;
    private String second_color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getFirst_color() {
        return first_color;
    }

    public void setFirst_color(String first_color) {
        this.first_color = first_color;
    }

    public String getSecond_color() {
        return second_color;
    }

    public void setSecond_color(String second_color) {
        this.second_color = second_color;
    }

    public int getId() {
        return id;
    }
}
