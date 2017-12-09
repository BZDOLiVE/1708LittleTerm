package gui;

import system.Database;
import system.Sln;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteSln_page {
    private JTextField tF_Name;
    private JTextArea tA_Doc;
    private JTextField tF_Deadline;
    private JButton btn_Commit;
    private JButton btn_return;
    private JButton btn_SignOut;
    private JLabel lbl_Title;
    private JLabel lbl_name;
    private JLabel lbl_deadline;
    private JLabel lbl_doc;
    public JPanel pnl_WriteSln_Page;
    private JPanel pnl_Text;
    private JPanel pnl_btn;

    private JFrame frame;
    private String authorization;
    private Database db;
    private String username;

    public WriteSln_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;
        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });

        btn_Commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sln s = new Sln();
                s.setName(tF_Name.getText());
                s.setDeadline(tF_Deadline.getText());
                s.setDoc(tA_Doc.getText());
                s.setAuthor(username);
                s.setStatus("未审核");
                db.addSln(s);
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
