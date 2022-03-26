package component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fence extends Box {
    final int WIDTH = 850;
    final int HEIGHT = 600;
    JFrame jf = null;
    private JTextArea jta = new JTextArea(WIDTH,520);



    public Fence(JFrame jf) {
        super(BoxLayout.Y_AXIS);
        this.jf=jf;
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        JButton input = new JButton("输入");
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Input(jf,"栅栏密码",true,jta).setVisible(true);

            }
        });


        btnPanel.add(input);
        this.add(btnPanel);
        this.add(new JScrollPane(jta));
    }

}
