package gui;

import system.Database;
import system.Sln;
import system.User;
import system.comment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DocView_page {
    private JTextArea tA_Doc;
    private JLabel lbl_id1;
    private JLabel lbl_id2;
    private JLabel lbl_name1;
    private JLabel lbl_name2;
    private JLabel lbl_author1;
    private JLabel lbl_author2;
    private JLabel lbl_deadline;
    private JLabel lbl_deadline2;
    private JLabel lbl_doc;
    public JPanel pnl_DocView_page;
    private JButton btn_Return;
    private JButton btn_SignOut;
    private JTextArea tF_Comm;
    private JButton btn_Bad;
    private JButton btn_Good;
    private JTable tbl_Comm;
    private JPanel pnl_Comm;
    private JPanel pnl_WriteComm;
    private JButton btn_Appro;
    private JButton btn_Conti;
    private JButton btn_Comm;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public DocView_page(JFrame _frame, String _authorization, String _username, Database _db, int id) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        Sln s = db.CheckOneSln(id);
        lbl_id2.setText(Integer.toString(s.getId()));
        lbl_name2.setText(s.getName());
        lbl_author2.setText(s.getAuthor());
        lbl_deadline2.setText(s.getDeadline());
        tA_Doc.setText(s.getDoc());


        if(authorization.equals("writer")){
            System.out.println("!!!");
            pnl_WriteComm.setVisible(false);
            btn_Good.setEnabled(false);
            btn_Bad.setEnabled(false);
            btn_Conti.setEnabled(false);
            btn_Comm.setVisible(false);
        }
        else{
            User u = db.CheckReturnUser(username);

            if(u.getJob().equals("否")){
                btn_Appro.setEnabled(false);
            }
            if(u.getZwh().equals("否")){
                btn_Good.setEnabled(false);
                btn_Bad.setEnabled(false);
            }
            else{
                if(!db.CheckAgree(id, username)){
                    btn_Bad.setEnabled(false);
                    btn_Good.setEnabled(false);
                }
            }
        }


        ArrayList<comment> commArrayList = db.Checkcomm(id);
        Object[][] datas = {};
        String[] titles = {"评论", "评论人"};
        DefaultTableModel model = new DefaultTableModel(datas, titles);

        for(comment c : commArrayList){
            model.addRow(new Object[] {c.getComm(), c.getName()});
        }
        tbl_Comm.setModel(model);


        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Sln_Search_page(frame, authorization, username, db).pnl_Sln_Search_page);
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
        btn_Good.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.AgreeSln(id,tF_Comm.getText(),username,true);
                btn_Good.setEnabled(false);
                btn_Bad.setEnabled(false);
            }
        });
        btn_Bad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.AgreeSln(id,tF_Comm.getText(),username,false);
                btn_Bad.setEnabled(false);
                btn_Good.setEnabled(false);
            }
        });
        btn_Appro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.ApproSln(id);
                frame.setContentPane(new Sln_Appro_page(frame, authorization, username, db).pnl_Sln_Appro);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Conti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Sln_Appro_page(frame, authorization, username, db).pnl_Sln_Appro);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Comm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.CommentSln(id,tF_Comm.getText(),username);
                model.addRow(new Object[] {tF_Comm.getText(), username});
                tF_Comm.setText("");
            }
        });
    }
}
