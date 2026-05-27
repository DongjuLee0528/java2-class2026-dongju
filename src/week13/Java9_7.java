package week13;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Java9_7 extends JFrame {

    private JLabel la = new JLabel("HELLO");

    public Java9_7() {

        super("상,하,좌,우 키를 이용하여 텍스트 움직이기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();

        c.setLayout(null);

        // 키 리스너 등록
        c.addKeyListener(new MyKeyListener());

        // 라벨 위치 설정
        la.setLocation(50, 50);

        // 라벨 크기 설정
        la.setSize(100, 20);

        c.add(la);

        setSize(200, 200);
        setVisible(true);

        // 포커스 설정
        c.setFocusable(true);
        c.requestFocus();
    }

    class MyKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            switch (keyCode) {

                case KeyEvent.VK_UP:
                    la.setLocation(la.getX(), la.getY() - 10);
                    break;

                case KeyEvent.VK_DOWN:
                    la.setLocation(la.getX(), la.getY() + 10);
                    break;

                case KeyEvent.VK_LEFT:
                    la.setLocation(la.getX() - 10, la.getY());
                    break;

                case KeyEvent.VK_RIGHT:
                    la.setLocation(la.getX() + 10, la.getY());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Java9_7();
    }
}