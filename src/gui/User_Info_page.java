package gui;

import system.Database;
import system.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Info_page {
    private JButton btn_Return;
    private JButton btn_SignOut;
    private JLabel lbl_name;
    private JLabel lbl_sex;
    private JLabel lbl_recommender;
    private JLabel lbl_recomm;
    public JPanel pnl_User_Info_page;

    private JFrame frame;
    private String authorization;
    private Database db;
    private String username;

    public User_Info_page(JFrame _frame, String _authorization, String _username, Database _db, String id) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        User u = db.CheckReturnUser(id);
        lbl_name.setText(u.getName());
        lbl_sex.setText(u.getSex());
        lbl_recommender.setText(u.getRecommender());
        lbl_recomm.setText(u.getRecomm());

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
                frame.setContentPane(new User_Mng_page(frame, authorization, username, db).pnl_User_Mng_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
    }
}
