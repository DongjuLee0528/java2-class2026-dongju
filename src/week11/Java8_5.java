package week11;

import java.awt.*;
import javax.swing.*;

public class Java8_5 extends JFrame {

    public Java8_5() {

        // 프레임 제목 설정
        super("GridLayout 예제");

        // X 버튼 클릭 시 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨텐트팬 가져오기
        Container contentPane = getContentPane();

        // 1행 10열 GridLayout 설정
        contentPane.setLayout(new GridLayout(1, 10));

        // 버튼 10개 생성 및 추가
        for (int i = 0; i < 10; i++) {

            // 숫자를 문자열로 변환
            String text = Integer.toString(i);

            // 버튼 생성
            JButton button = new JButton(text);

            // 컨텐트팬에 버튼 추가
            contentPane.add(button);
        }

        // 프레임 크기 설정
        setSize(500, 200);

        // 프레임 화면 출력
        setVisible(true);
    }

    public static void main(String[] args) {

        // 객체 생성
        new Java8_5();
    }
}