package com.example.librarystar.Models;

import java.util.Date;
    public class Action {
        private int id;
        public Action(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }
    }