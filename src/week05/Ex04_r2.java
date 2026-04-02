package week05;

public class Ex04_r2 {
    public static void main(String[] args) {


        for (int i = 2; i <= 9; i++) {
            System.out.print("[" + i + "단]\t");
        }
        System.out.println();


        for (int j = 1; j <= 9; j++) {
            for (int i = 2; i <= 9; i++) {
                System.out.print(i + "X" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }

    }
}
