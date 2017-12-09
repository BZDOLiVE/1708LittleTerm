package gui;

import system.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Recomm2_page {
    private JTextArea tA_Message;
    private JButton btn_Recomm;
    private JButton btn_Return;
    private JButton btn_SignOut;
    public JPanel pnl_Recomm2_page;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public Recomm2_page(JFrame _frame, String _authorization, String _username, Database _db, String recusername) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;


        btn_SignOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Login_GUI(frame, db).pnl_Login);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Recomm1_page(frame, authorization, username, db).pnl_Recomm1_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Recomm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.UserRecomm(tA_Message.getText(), recusername);
                frame.setContentPane(new Recomm1_page(frame, authorization, username, db).pnl_Recomm1_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
    }
}
