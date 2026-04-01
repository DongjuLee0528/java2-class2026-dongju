package week05;

import java.util.Scanner;

public class Ex42 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("정수를 5개 입력하세요.");
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            int n = scanner.nextInt();

            if (n <= 0) continue; // 0 또는 음수는 건너뜀
            else sum += n;        // 양수만 더함
        }

        System.out.println("양수의 합은 " + sum);

        scanner.close();
    }
}
