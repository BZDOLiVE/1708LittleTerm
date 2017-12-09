package gui;

import system.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chose_Fuc_page {
    public JPanel Chose_Fuc_page;
    private JButton btn_SlnSrch;
    private JButton btn_SlnWrt;
    private JButton btn_InfoMng;
    private JButton btn_UserMng;
    private JButton btn_SlnAppro;
    private JButton btn_prvtSlnMng;
    private JButton btn_SignOut;
    private JButton btn_Recomm;

    private JFrame frame;
    private String authorization;
    private Database db;
    private String username;

    public Chose_Fuc_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;
        if(authorization.equals("writer")){
            btn_UserMng.setEnabled(false);
            btn_SlnAppro.setEnabled(false);
            //btn_Recomm.setEnabled(false);
        }
        btn_SlnSrch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Sln_Search_page(frame, authorization, username, db).pnl_Sln_Search_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_SlnWrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new WriteSln_page(frame, authorization, username, db).pnl_WriteSln_Page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_InfoMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Info_Mng_page(frame, authorization, username, db).pnl_Info_Mng_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_UserMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new User_Mng_page(frame, authorization, username, db).pnl_User_Mng_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_SlnAppro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Sln_Appro_page(frame, authorization, username, db).pnl_Sln_Appro);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });

        btn_prvtSlnMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new prvtSln_Mng_page(frame, authorization, username, db).pnl_prvtSln_Mng_page);
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
        btn_Recomm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Recomm1_page(frame, authorization, username, db).pnl_Recomm1_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
    }
}
