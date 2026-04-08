package week06;

import java.util.Scanner;

// Ex13.java
// 예제3-13: 나눔수와 나눗수를 입력받아 몫을 구하는 프로그램(예외 처리)

public class Ex13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dividend; // 나눔수
        int divisor;  // 나눗수

        System.out.print("나눔수를 입력하시오:");
        dividend = scanner.nextInt(); // 나눔수 입력

        System.out.print("나눗수를 입력하시오:");
        divisor = scanner.nextInt(); // 나눗수 입력

        try {
            System.out.println(dividend + "를 " + divisor + "로 나누면 몫은 "
                    + dividend / divisor + "입니다.");
        }
        catch (ArithmeticException e) { // ArithmeticException 예외 처리 코드
            System.out.println("0으로 나눌 수 없습니다!");
        }
        finally {
            scanner.close(); // 정상적이든 예외가 발생하든 최종적으로 scanner를 닫는다.
        }
    }
}
