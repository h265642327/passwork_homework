package component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RomeInput extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 400;
    JTextArea jta;
    Boolean isCiphertext = false;

    public RomeInput(JFrame jf, String title, Boolean isModel, JTextArea jta) {
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

        Box keyBox = Box.createHorizontalBox();
        JLabel keyLabel = new JLabel("密钥");
        JTextField keyField = new JTextField();
        keyBox.add(keyLabel);
        keyBox.add(keyField);

        JButton okBtn = new JButton("确认");
        Box okBox = Box.createHorizontalBox();
        okBox.add(okBtn);


        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(80));
        vBox.add(typeBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(addBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(keyBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(okBox);
        vBox.add(Box.createVerticalStrut(80));

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
                String key = keyField.getText().trim();
                String answer;
                if (isCiphertext) {
                    answer = decryptProcess(rome, key);
                    jta.append("解密成功：" + answer + "\n");
                } else {
                    answer = encryptProcess(rome, key);
                    jta.append("加密成功：" + answer + "\n");
                }
                dispose();
            }
        });

        this.add(hBox);

    }

    public String encryptProcess(String rome, String key) {
        StringBuilder answer = new StringBuilder("");
        int keyNum = Integer.parseInt(key);
        keyNum = keyNum % 26;
        for (int i = 0; i < rome.length(); i++) {
            answer.append((char) (rome.charAt(i) + keyNum));
        }
        return answer.toString();
    }

    public String decryptProcess(String rome, String key) {
        StringBuilder answer = new StringBuilder("");
        int keyNum = Integer.parseInt(key);
        keyNum = keyNum % 26;
        for (int i = 0; i < rome.length(); i++) {
            answer.append((char) (rome.charAt(i) - keyNum));
        }
        return answer.toString();
    }
}
