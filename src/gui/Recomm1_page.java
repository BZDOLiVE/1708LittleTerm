package gui;

import system.Database;
import system.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Recomm1_page {
    private JButton btn_SignOut;
    private JTable tbl_User;
    private JButton btn_Recomm;
    private JButton btn_Return;
    public JPanel pnl_Recomm1_page;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public Recomm1_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        ArrayList<User> UserArrayList = db.CheckRecommUser(username);
        Object[][] datas = {};
        String[] titles = {"用户名", "姓名", "推荐人"};
        DefaultTableModel model = new DefaultTableModel(datas, titles);

        if(!UserArrayList.isEmpty()) {
            for (User u : UserArrayList) {
                model.addRow(new Object[]{u.getUsername(), u.getName(), u.getRecommender()});
            }
        }
        tbl_User.setModel(model);
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
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Recomm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tbl_User.getRowCount(); i++){
                    if(tbl_User.isRowSelected(i)){
                        String recusername = tbl_User.getValueAt(i,0).toString();
                        frame.setContentPane(new Recomm2_page(frame, authorization, username, db, recusername).pnl_Recomm2_page);
                        frame.pack();
                        frame.setBounds(frame.getX(), frame.getY(), 600, 450);
                    }

                }
            }
        });
    }
}
