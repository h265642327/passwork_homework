package component;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JDialog {
    final int WIDTH=400;
    final int HEIGHT=400;
    final int NUMBER=100;
    Boolean isCiphertext = false;
    JTextArea jta;

    public Input(JFrame jf, String title, Boolean isModel,JTextArea jta){
        super(jf,title,isModel);
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.jta=jta;

//        选择按钮
        Box typeBox = Box.createHorizontalBox();
        ButtonGroup bg = new ButtonGroup();
        JRadioButton ciphertext = new JRadioButton("密文",false);
        JRadioButton originalText =new JRadioButton("原文",true);
        bg.add(ciphertext);
        bg.add(originalText);
        typeBox.add(ciphertext);
        typeBox.add(originalText);



        Box fenceBox = Box.createHorizontalBox();
        JLabel fenceLabel = new JLabel("输入");
        JTextField fenceField = new JTextField();



        fenceBox.add(fenceLabel);
        fenceBox.add(fenceField);

        Box keyBox = Box.createHorizontalBox();
        JLabel keyLabel = new JLabel("密钥");
        JTextField keyField = new JTextField();
        keyBox.add(keyLabel);
        keyBox.add(keyField);

        Box okBox = Box.createHorizontalBox();
        JButton okBtn = new JButton("确认");
        okBox.add(okBtn);


        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(80));
        vBox.add(typeBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(fenceBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(keyBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(okBox);
        vBox.add(Box.createVerticalStrut(80));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

//        设置反馈

//        原文密文的选择反馈
        originalText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCiphertext=false;
            }
        });
        ciphertext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isCiphertext=true;
            }
        });

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fence = fenceField.getText().trim();
                String key = keyField.getText().trim();
                String answer;
                if(isCiphertext){
                    answer=decryptProcess(fence,key);
                    jta.append("解密成功： "+answer+"\n");
                } else {
                    answer=encryptProcess(fence,key);
                    jta.append("加密成功： "+answer+"\n");
                }
                dispose();
            }

        });



        this.add(hBox);
    }


    public String encryptProcess(String fence,String key){
        StringBuilder answer = new StringBuilder("");
        int fenceNum = Integer.parseInt(key);
        int row[] = new int[NUMBER];
        int textLength = fence.length();
        int q=textLength % fenceNum;
        for(int i=1;i<=fenceNum;i++){
            row[i]=textLength/fenceNum;
        }
        for(int i=1;i<=q;i++){
            row[i]++;
        }
        int rowNum = 1;
        for(int i=0;i<fenceNum;i++){
            for(int j=i;j<fence.length();j=j+fenceNum){
                answer.append(fence.charAt(j));
            }
        }
        return answer.toString();
    }

    public String decryptProcess(String fence,String key){
        StringBuilder answer = new StringBuilder("");
        int []row=new int [NUMBER];
        int keyNum = Integer.parseInt(key);
        int q=fence.length()%keyNum;
        for(int  i=1;i<keyNum;i++){
            row[i]=fence.length()/keyNum;
        }
        for(int i=1;i<=q;i++){
            row[i]++;
        }
        int []mark= new int[NUMBER];
        int rowNum=1;
        for(int i=0;i<row[1];i++){
            for(int j=i;j<fence.length()&&rowNum<=4;j=j+row[rowNum++]){
                if(mark[j]==0) {
                    answer.append(fence.charAt(j));
                    mark[j]++;
                }
            }
            rowNum=1;
        }
        return answer.toString();
    }
}
