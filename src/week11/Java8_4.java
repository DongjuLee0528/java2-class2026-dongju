package week11;

import javax.swing.*;
import java.awt.*;

public class Java8_4 extends JFrame {

    public Java8_4() {

        // 프레임 제목 설정
        setTitle("BorderLayout 예제");

        // X 버튼 클릭 시 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨텐트팬 가져오기
        Container contentPane = getContentPane();

        // BorderLayout 배치관리자 설정
        // 가로 간격 30, 세로 간격 20
        contentPane.setLayout(new BorderLayout(30, 20));

        // 버튼 추가 및 위치 지정
        add(new JButton("Calculate"), BorderLayout.CENTER);
        add(new JButton("add"), BorderLayout.NORTH);
        add(new JButton("sub"), BorderLayout.SOUTH);
        add(new JButton("mul"), BorderLayout.EAST);
        add(new JButton("div"), BorderLayout.WEST);

        // 프레임 크기 설정
        setSize(300, 200);

        // 프레임 화면 출력
        setVisible(true);
    }

    public static void main(String[] args) {

        // 객체 생성
        new Java8_4();
    }
}