import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nВыберите задачу:\n1. For.12\n2. While.17");
            int choice = s.nextInt();
            if (choice == 1) {
                for12();
            } else if (choice == 2) {
                while17();
            } else {
                System.out.println("Нет такой задачи блин!!!!!!");
            }
        }
    }

    /**
     * Дано целое число N (> 0). Найти произведение
     * 1.1 · 1.2 · 1.3 · . . .
     * (N сомножителей).
     */
    public static void for12(){
        System.out.println("Введите количество сомножителей:");
        float result = 1.0f;
        int N = s.nextInt();
        if (N <= 0){
            System.out.println("Число сомножителей должно быть больше нуля!");
        }
        else {
            for (int i = 0; i <= N; i++) {
                result *= 1.0f + (i * 0.1f);
            }
            System.out.println("Полученное произведение - " + result);
        }
    }

    /**
     * Дано целое число N (> 0). Используя операции деления нацело и
     * взятия остатка от деления, вывести все его цифры, начиная с самой правой
     * (разряда единиц).
     */
    public static void while17(){
        System.out.println("Введите число:");
        int N = s.nextInt();
        if (N <= 0){
            System.out.println("Число не должно быть меньше нуля!");
        }
        else {
            for (int i = 0; i < String.valueOf(N).length(); i++) {
                System.out.print((int) (N % Math.pow(10, i + 1) / Math.pow(10, i)) + "   ");
            }
        }
    }
}