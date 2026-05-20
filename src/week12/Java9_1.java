package week12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Java9_1 extends JFrame {

    public Java9_1() {

        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JButton btn = new JButton("Action");

        // 버튼에 이벤트 리스너 등록
        btn.addActionListener(new Ex91MyActionListener());

        c.add(btn);

        setSize(250, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Java9_1();
    }
}

// 독립 클래스로 이벤트 리스너 작성
class Ex91MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        // 이벤트가 발생한 버튼 가져오기
        JButton b = (JButton)e.getSource();

        // 버튼 문자열 비교
        if(b.getText().equals("Action")) {
            b.setText("액션");
        }
        else {
            b.setText("Action");
        }
    }
}