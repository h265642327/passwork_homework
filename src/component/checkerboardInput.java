package component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkerboardInput extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    JTextArea jta;
    Boolean isCiphertext = false;

    public checkerboardInput(JFrame jf, String title, Boolean isModel, JTextArea jta) {
        super(jf, title, isModel);
        this.jta = jta;

        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        Box typeBox = Box.createHorizontalBox();
        ButtonGroup bg = new ButtonGroup();
        JRadioButton ciphertext = new JRadioButton("密文", false);
        JRadioButton originalText = new JRadioButton("原文", true);
        bg.add(ciphertext);
        bg.add(originalText);
        typeBox.add(ciphertext);
        typeBox.add(originalText);


        Box addBox = Box.createHorizontalBox();
        JLabel addLabel = new JLabel("输入");
        JTextField addField = new JTextField();
        addBox.add(addLabel);
        addBox.add(addField);



        JButton okBtn = new JButton("确认");
        Box okBox = Box.createHorizontalBox();
        okBox.add(okBtn);


        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(95));
        vBox.add(typeBox);
        vBox.add(Box.createVerticalStrut(75));
        vBox.add(addBox);
        vBox.add(Box.createVerticalStrut(75));
        vBox.add(okBox);
        vBox.add(Box.createVerticalStrut(95));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));


//        监听选择按钮
        ciphertext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCiphertext = true;
            }
        });
        originalText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCiphertext = false;
            }
        });

//        监听确认按钮
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rome = addField.getText().trim();

                String answer;
                if (isCiphertext) {
                    answer = decryptProcess(rome);
                    jta.append("解密成功：" + answer + "\n");
                } else {
                    answer = encryptProcess(rome);
                    jta.append("加密成功：" + answer + "\n");
                }
                dispose();
            }
        });

        this.add(hBox);

    }

    public String encryptProcess(String rome) {
        StringBuilder answer = new StringBuilder("");
        for (int i = 0; i < rome.length(); i++) {
            char a=rome.charAt(i);
            if((rome.charAt(i)>='a'&&rome.charAt(i)<='e')||(rome.charAt(i)>='A'&&rome.charAt(i)<='E')){
                if(a>='a'){
                    answer.append(a-86);
                }
                else{
                    answer.append(a-54);
                }
            }
            else if((rome.charAt(i)>='f'&&rome.charAt(i)<='k')||(rome.charAt(i)>='F'&&rome.charAt(i)<='K')){
                if(a=='i'||a=='I'||a=='j'||a=='J'){
                    answer.append(24);
                }
                else if(a>='f'){
                    answer.append(a-81);
                }
                else{
                    answer.append(a-49);
                }
            }
            else if((rome.charAt(i)>='l'&&rome.charAt(i)<='p')||(rome.charAt(i)>='L'&&rome.charAt(i)<='P')){
                if(a>='l'){
                    answer.append(a-77);
                }
                else{
                    answer.append(a-45);
                }
            }
            else if((rome.charAt(i)>='q'&&rome.charAt(i)<='u')||(rome.charAt(i)>='Q'&&rome.charAt(i)<='U')){
                if(a>='q'){
                    answer.append(a-72);
                }
                else{
                    answer.append(a-40);
                }
            }
            else if((rome.charAt(i)>='v'&&rome.charAt(i)<='z')||(rome.charAt(i)>='V'&&rome.charAt(i)<='Z')){
                if(a>='v'){
                    answer.append(a-67);
                }
                else{
                    answer.append(a-35);
                }
            }
        }
        return answer.toString();
    }

    public String decryptProcess(String rome) {
        StringBuilder answer = new StringBuilder("");
        for (int i = 0; i < rome.length(); i=i+2) {
            StringBuilder a = new StringBuilder();
            for(int j=i;j<i+2;j++){
                a.append(rome.charAt(j));
            }
            int num = Integer.parseInt(a.toString());
            switch (num){
                case 11:
                    answer.append('a');
                    break;
                case 12:
                    answer.append('b');
                    break;
                case 13:
                    answer.append('c');
                    break;
                case 14:
                    answer.append('d');
                    break;
                case 15:
                    answer.append('e');
                    break;
                case 21:
                    answer.append('f');
                    break;
                case 22:
                    answer.append('g');
                    break;
                case 23:
                    answer.append('h');
                    break;
                case 24:
                    answer.append("(i/j)");
                    break;
                case 25:
                    answer.append('k');
                    break;
                case 31:
                    answer.append('l');
                    break;
                case 32:
                    answer.append('m');
                    break;
                case 33:
                    answer.append('n');
                    break;
                case 34:
                    answer.append('o');
                    break;
                case 35:
                    answer.append('p');
                    break;
                case 41:
                    answer.append('q');
                    break;
                case 42:
                    answer.append('r');
                    break;
                case 43:
                    answer.append('s');
                    break;
                case 44:
                    answer.append('t');
                    break;
                case 45:
                    answer.append('u');
                    break;
                case 51:
                    answer.append('v');
                    break;
                case 52:
                    answer.append('w');
                    break;
                case 53:
                    answer.append('x');
                    break;
                case 54:
                    answer.append('y');
                    break;
                case 55:
                    answer.append('z');
                    break;
            }
        }
        return answer.toString();
    }
}
