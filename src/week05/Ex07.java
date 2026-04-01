package week05;

import java.util.Scanner;

public class Ex07 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int intArray[];
        intArray = new int[5];
        // 또는
        // int[] intArray = new int[5];

        int max = 0; // 현재 가장 큰 수

        System.out.println("양수 5개를 입력하세요.");

        for (int i = 0; i < 5; i++) {
            intArray[i] = scanner.nextInt(); // 입력값 저장

            if (intArray[i] > max) {
                max = intArray[i]; // 최대값 갱신
            }
        }

        System.out.println("가장 큰 수는 " + max + "입니다.");

        scanner.close();
    }
}
