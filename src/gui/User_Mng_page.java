package gui;

import system.Database;
import system.Sln;
import system.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class User_Mng_page {
    private JTable tbl_User;
    private JButton btn_rjt;
    private JButton btn_Return;
    private JButton btn_SignOut;
    private JTextField tF_Name;
    private JButton btn_Search;
    private JButton btn_admin;
    private JButton btn_wrt;
    public JPanel pnl_User_Mng_page;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public User_Mng_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        ArrayList<User> UserArrayList = db.CheckSignUpUser();
        Object[][] datas = {};
        String[] titles = {"用户名", "姓名", "推荐人"};
        DefaultTableModel model = new DefaultTableModel(datas, titles);

        for(User u : UserArrayList){
            model.addRow(new Object[] {u.getUsername(), u.getName(), u.getRecommender()});
        }
        tbl_User.setModel(model);

        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_wrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tbl_User.getRowCount(); i++){
                    if(tbl_User.isRowSelected(i)){
                        db.UpdateUserAth(tbl_User.getValueAt(i,0).toString(), "writer");
                        model.removeRow(i);
                    }

                }
            }
        });
        btn_admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tbl_User.getRowCount(); i++){
                    if(tbl_User.isRowSelected(i)){
                        db.UpdateUserAth(tbl_User.getValueAt(i,0).toString(), "admin");
                        model.removeRow(i);
                    }

                }
            }
        });
        btn_rjt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tbl_User.getRowCount(); i++){
                    if(tbl_User.isRowSelected(i)){
                        db.DeleteUser(tbl_User.getValueAt(i,0).toString());
                        model.removeRow(i);
                    }

                }
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
        btn_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = tF_Name.getText();
                for(int i = 0; i < tbl_User.getRowCount(); i++){
                    if(n.equals(tbl_User.getValueAt(i,0).toString())){
                        frame.setContentPane(new User_Info_page(frame, authorization, username, db, tF_Name.getText()).pnl_User_Info_page);
                        frame.pack();
                        frame.setBounds(frame.getX(), frame.getY(), 600, 450);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "待审批用户无此人","错误",JOptionPane.ERROR_MESSAGE);
                tF_Name.setText("");
            }
        });
    }
}
