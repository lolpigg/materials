import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Добрый день! Выберите действия:\n1. Сложение\n2. Вычитание\n3. Деление\n4. Умножение\n5. Степень\n6. Выход из программы");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if (choice.equals("6")) {
                System.exit(0);
            } else if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) {
                System.out.println("Действие не найдено.");
            } else {
                System.out.println("Введите первое число.");
                int FirstNum = input.nextInt();
                System.out.println("Введите второе число.");
                int SecondNum = input.nextInt();
                switch (choice) {
                    case "1":
                        System.out.println(FirstNum + SecondNum);
                        break;
                    case "2":
                        System.out.println(FirstNum - SecondNum);
                        break;
                    case "3":
                        System.out.println(FirstNum / SecondNum);
                        break;
                    case "4":
                        System.out.println(FirstNum * SecondNum);
                        break;
                    case "5":
                        System.out.println(Math.pow(FirstNum, SecondNum));
                        break;
                }
            }
        }
    }
}