package week11;

import javax.swing.JFrame;

public class Java0513 extends JFrame {

    public Java0513() {

        // 프레임 제목 설정
        setTitle("300x300 스윙 프레임 만들기");

        // 프레임 크기 설정
        setSize(300, 300);

        // 프레임 화면 출력
        setVisible(true);
    }

    public static void main(String[] args) {

        // 객체 생성
        new Java0513();
    }
}