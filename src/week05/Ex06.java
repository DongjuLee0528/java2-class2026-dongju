package week05;

import java.util.Scanner;

public class Ex06 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("exit를 입력하면 종료합니다.");

        while (true) {
            System.out.print(">>");
            String text = scanner.nextLine();

            if (text.equals("exit")) // "exit" 입력 시 종료
                break; // while문 탈출
        }

        System.out.println("종료합니다...");
        scanner.close();
    }
}
