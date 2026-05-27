package week13;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Java10_1 extends JFrame {

    public Java10_1() {

        super("JComponent의 공통 메소드 예제");

        Container c = getContentPane();

        c.setLayout(new FlowLayout());

        JButton b1 = new JButton("Magenta/Yellow Button");
        JButton b2 = new JButton("Disabled Button");
        JButton b3 = new JButton("getX(), getY()");

        // 버튼 색상 설정
        b1.setBackground(Color.YELLOW);
        b1.setForeground(Color.MAGENTA);

        // 버튼 폰트 설정
        b1.setFont(new Font("Arial", Font.ITALIC, 20));

        // 버튼 비활성화
        b2.setEnabled(false);

        // 버튼 클릭 이벤트
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JButton b = (JButton)e.getSource();

                setTitle(b.getX() + ", " + b.getY());
            }
        });

        c.add(b1);
        c.add(b2);
        c.add(b3);

        setSize(260, 200);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Java10_1();
    }
}