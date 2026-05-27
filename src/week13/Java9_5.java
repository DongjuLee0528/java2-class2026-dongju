package week13;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Java9_5 extends JFrame {

    private JLabel la = new JLabel("Hello");

    public Java9_5() {

        setTitle("Mouse 이벤트 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();

        // 마우스 리스너 등록
        c.addMouseListener(new MyMouseAdapter());

        c.setLayout(null);

        // 레이블 크기 설정
        la.setSize(50, 20);

        // 레이블 위치 설정
        la.setLocation(30, 30);

        c.add(la);

        setSize(200, 200);
        setVisible(true);
    }

    // MouseAdapter 상속
    class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            // 마우스 클릭 좌표
            int x = e.getX();
            int y = e.getY();

            // 레이블 위치 이동
            la.setLocation(x, y);
        }
    }

    public static void main(String[] args) {
        new Java9_5();
    }
}