package week06;

import java.util.Scanner;

public class Ex12 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dividend; // 나눔수
        int divisor;  // 나눌수

        System.out.print("나눔수를 입력하시오:");
        dividend = scanner.nextInt(); // 나눔수 입력

        System.out.print("나눌수를 입력하시오:");
        divisor = scanner.nextInt(); // 나눌수 입력

        System.out.println(dividend + "를 " + divisor + "로 나누면 몫은 "
                + dividend / divisor + "입니다.");

        scanner.close(); // Scanner 객체 닫기.(중요!)
    }
}
