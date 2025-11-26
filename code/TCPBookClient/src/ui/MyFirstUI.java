/*
 * Created by JFormDesigner on Mon Oct 27 20:27:53 CST 2025
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import control.Operator;
import net.Client;

/**
 * @author z1733
 */
public class MyFirstUI extends JFrame {
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFirstUI frame = new MyFirstUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MyFirstUI() {
        super("图书管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 550, 400);
        initComponents();
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                Client.write("0\n");
            }
        });
    }

    private void AddBookUI(ActionEvent e) {
        AddBookUI addbookui = new AddBookUI();
        addbookui.setVisible(true);
    }
    
    private void DelBookUI(ActionEvent e) {
        DelBookUI delbookui = new DelBookUI();
        delbookui.setVisible(true);
    }
    
    private void AltBookUI(ActionEvent e) {
        AltBookUI altbookui = new AltBookUI();
        altbookui.setVisible(true);
    }
    
    private void FndBookUI(ActionEvent e) {
        FndBookUI selbookui = new FndBookUI();
        selbookui.setVisible(true);
    }
    
    private void PrtBookUI(ActionEvent e) {
        Operator operator = new Operator();
        String[][] booklist = operator.prtAllBook();
        BookListUI booklistui = new BookListUI(booklist);
        booklistui.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        label1 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u589e\u52a0\u56fe\u4e66");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(75, 80), button1.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u6765\u5230\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(130, 30), label1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5220\u9664\u56fe\u4e66");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(230, 80), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u4fee\u6539\u56fe\u4e66");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(75, 145), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u67e5\u8be2\u56fe\u4e66");
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(230, 145), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u6253\u5370\u56fe\u4e66\u5217\u8868");
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(140, 205), button5.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(410, 305));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
