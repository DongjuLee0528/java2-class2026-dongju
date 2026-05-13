package week11;

import javax.swing.*;
import java.awt.*;

public class Java8_2 extends JFrame {

    public Java8_2() {

        // 프레임 제목 설정
        setTitle("ContentPane과 JFrame 예제");

        // 종료 버튼 클릭 시 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경색 설정
        setBackground(Color.ORANGE);

        // 배치 방식 설정
        setLayout(new FlowLayout());

        // 버튼 추가
        add(new JButton("OK"));
        add(new JButton("Cancel"));
        add(new JButton("Ignore"));

        // 프레임 크기 설정
        setSize(300, 150);

        // 프레임 화면 출력
        setVisible(true);
    }

    public static void main(String[] args) {

        // 객체 생성
        new Java8_2();
    }
}