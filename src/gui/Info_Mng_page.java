package gui;

import system.Database;
import system.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2017/8/23.
 */
public class Info_Mng_page {
    private JTextField tF_Name;
    private JTextField tF_Recom;
    private JTextField tF_Telnum;
    private JTextField tF_Address;
    private JLabel lbl_Title2;
    private JLabel lbl_Title1;
    public JPanel pnl_Info_Mng_page;
    private JPanel pnl_Title;
    private JLabel lbl_Name;
    private JLabel lbl_Sex;
    private JLabel lbl_Address;
    private JLabel lbl_Telnum;
    private JLabel lbl_Recom;
    private JLabel lbl_Job;
    private JLabel lbl_Zwh;
    private JTextField tF_Password;
    private JLabel lbl_Password;
    private JLabel lbl_Username;
    private JLabel lbl_Username2;
    private JComboBox cB_Sex;
    private JComboBox cB_Job;
    private JComboBox cB_Zwh;
    private JButton btn_SignOut;
    private JPanel pnl_Title3;
    private JPanel pnl_Title2;
    private JPanel pnl_Field;
    private JPanel pnl_Button;
    private JButton btn_Save;
    private JButton btn_Return;
    private JLabel lbl_Ath;
    private JLabel lbl_Ath2;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;
    private User u;

    public Info_Mng_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;
        u = db.CheckReturnUser(username);
        lbl_Username2.setText(u.getUsername());
        lbl_Ath2.setText(u.getAuthorization());
        tF_Password.setText(u.getPassword());
        tF_Name.setText(u.getName());
        tF_Address.setText(u.getAddress());
        tF_Telnum.setText(u.getTelnum());
        tF_Recom.setText(u.getRecommender());
        if(!u.getSex().equals("?") && !u.getSex().equals(null)) {
            cB_Sex.setSelectedItem(u.getSex());
        }
        if(!u.getJob().equals("?") && !u.getJob().equals(null)){
            cB_Job.setSelectedItem(u.getJob());
        }
        if(!u.getZwh().equals("?") && !u.getJob().equals(null)){
            cB_Zwh.setSelectedItem(u.getZwh());
        }




        btn_Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                u.setPassword(tF_Password.getText());
                u.setName(tF_Name.getText());
                u.setSex(cB_Sex.getSelectedItem().toString());
                u.setAddress(tF_Address.getText());
                u.setTelnum(tF_Telnum.getText());
                u.setRecommender(tF_Recom.getText());
                u.setJob(cB_Job.getSelectedItem().toString());
                u.setZwh(cB_Zwh.getSelectedItem().toString());
                db.UpdateUser(u);
            }
        });

        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_SignOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Login_GUI(frame, db).pnl_Login);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
    }
}
