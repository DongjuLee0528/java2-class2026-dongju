package week04;

import java.util.Scanner;

public class Boo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();
        System.out.println("당신의 이름은 " + name + "입니다.");

        String city = scanner.next();
        System.out.println("당신이 사는 도시는 " + city + "입니다.");

        int age = scanner.nextInt();
        System.out.println("당신의 나이는 " + age + "살입니다.");

        double weight = scanner.nextDouble();
        System.out.println("당신의 체중은 " + weight + "kg입니다.");

        boolean single = scanner.nextBoolean();
        System.out.println("당신은 독신 여부는 " + single + "입니다.");

        scanner.close();
    }
}