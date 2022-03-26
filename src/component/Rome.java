package component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rome extends Box {
    final int WIDTH = 850;
    final int HEIGHT = 600;
    JFrame jf=null  ;
    JTextArea jta= new JTextArea(WIDTH,HEIGHT-80);

    public Rome(JFrame jf){
        super(BoxLayout.Y_AXIS);
        this.jf=jf;



        JPanel btnPanel = new JPanel();
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("输入");
        btnPanel.add(addBtn);

//        监听
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RomeInput(jf,"罗马密码",true,jta).setVisible(true);
            }
        });

        this.add(btnPanel);
        this.add(new JScrollPane(jta));
    }
}
