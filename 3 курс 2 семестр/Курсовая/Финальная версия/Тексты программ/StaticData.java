package com.example.librarystar;

public class StaticData {
    static int user_id = 0;
    static String user_role = "Пользователь";
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (password.contains(" ")) {
            return false;
        }
        if (!password.matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }

}
