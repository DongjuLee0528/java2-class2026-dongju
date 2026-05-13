package week11;

import javax.swing.*;
import java.awt.*;

public class Java8_3 extends JFrame {

    public Java8_3() {

        // 프레임 제목 설정
        setTitle("FlowLayout 예제");

        // X 버튼 클릭 시 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨텐트팬 가져오기
        Container contentPane = getContentPane();

        // 왼쪽 정렬, 수평 간격 30, 수직 간격 40 설정
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));

        // 버튼 추가
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        contentPane.add(new JButton("Calculate"));

        // 프레임 크기 설정
        setSize(300, 200);

        // 프레임 화면 출력
        setVisible(true);
    }

    public static void main(String[] args) {

        // 객체 생성
        new Java8_3();
    }
}