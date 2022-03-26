import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import component.Fence;
import component.Rome;
import component.ScreenUtils;
import component.checkerboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    final int WIDTH = 1000;
    final int HEIGHT = 600;
    JFrame jf = new JFrame("大作业By黄家杰");
    JMenuBar jMenuBar = new JMenuBar();
    JMenu menu = new JMenu("菜单");
    JMenuItem exit = new JMenuItem("退出");
    JSplitPane jsp = new JSplitPane();


    public void init() {

//        大小以及位置
        jf.setBounds((new ScreenUtils().getScreenWidth() - WIDTH) / 2,
                (new ScreenUtils().getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//        菜单
        jMenuBar.add(menu);
        menu.add(exit);
        jf.setJMenuBar(jMenuBar);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


//          分割位置
        jsp.setDividerLocation(150);
//        厚度
        jsp.setDividerSize(7);
        jsp.setContinuousLayout(true);

//      左侧
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("密码");
        DefaultMutableTreeNode fencePassword = new DefaultMutableTreeNode("栅栏密码");
        DefaultMutableTreeNode romePassword = new DefaultMutableTreeNode("罗马密文");
        DefaultMutableTreeNode checkerboardPassword = new DefaultMutableTreeNode("棋盘密码");
        root.add(romePassword);
        root.add(fencePassword);
        root.add(checkerboardPassword);
        JTree jTree = new JTree(root);
        jTree.setSelectionRow(2);
        jsp.setRightComponent(new Fence(jf));
        //jsp.setRightComponent(new JLabel("这里是成绩管理，但是还未开发。杰哥忙不过了"));
        jsp.setLeftComponent(jTree);

//        监听
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object o = e.getNewLeadSelectionPath().getLastPathComponent();
                if (o.equals(romePassword)) {
                    jsp.setRightComponent(new Rome(jf));
                    jsp.setDividerLocation(150);
                } else if (o.equals(fencePassword)) {
                    jsp.setRightComponent(new Fence(jf));
                    jsp.setDividerLocation(150);
                }
                else if (o.equals(checkerboardPassword)){
                    jsp.setRightComponent(new checkerboard(jf));
                    jsp.setDividerLocation(150);
                }
            }
        });


        jf.add(jsp);
        //jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Test().init();
    }
}
