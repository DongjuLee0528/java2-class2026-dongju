package week05;

public class Ex04 {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) { // 단 (1~9)
            for (int j = 1; j < 10; j++) { // 곱하는 수 (1~9)
                System.out.print(i + "*" + j + "=" + (i * j));
                System.out.print("\t"); // 탭으로 구분
            }
            System.out.println(); // 한 줄 내려감
        }

    }
}
