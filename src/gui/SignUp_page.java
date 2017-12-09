package gui;

import system.Database;
import system.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp_page {
    public JPanel SignUp_page;
    private JTextField tF_Username;
    private JTextField tF_Password;
    private JTextField tF_Name;
    private JTextField tF_Address;
    private JTextField tF_Telnum;
    private JTextField tF_Recom;
    private JButton btn_SignUp;
    private JButton btn_Clear;
    private JButton btn_Return;
    private JComboBox cB_Job;
    private JComboBox cB_Zwh;
    private JLabel lbl_Titl;
    private JLabel lbl_Username;
    private JLabel lbl_password;
    private JLabel lbl_name;
    private JComboBox cB_Sex;
    private JLabel lbl_Sex;
    private JLabel lbl_Birthday;
    private JLabel lbl_Address;
    private JLabel lbl_Telnum;
    private JLabel lbl_Recom;
    private JLabel lbl_Job;
    private JLabel lbl_zwh;
    private JComboBox cB_Year;
    private JComboBox cB_Date;
    private JComboBox cB_Month;

    private JFrame frame;
    Database db;
    public SignUp_page(JFrame _frame, Database _db) {
        this.frame = _frame;
        this.db = _db;

        btn_SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tF_Username.getText(); String password = tF_Password.getText();
                if(username.equals("")){
                    JOptionPane.showMessageDialog(frame, "必须输入用户名","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean flag = db.CheckUsername(username);
                if(flag && !password.equals("")){
                    if(username.length() > 15){
                        JOptionPane.showMessageDialog(frame, "用户名长度不得超过15字符","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(password.length() > 15){
                        JOptionPane.showMessageDialog(frame, "密码长度不得超过15字符","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    User u = new User(username, password);
                    u.setBirthday(cB_Year.getSelectedItem().toString(), cB_Month.getSelectedItem().toString(), cB_Date.getSelectedItem().toString());
                    u.setInformation(tF_Name.getText(),cB_Sex.getSelectedItem().toString(), tF_Address.getText(), tF_Telnum.getText(), tF_Recom.getText()
                    , cB_Job.getSelectedItem().toString(), cB_Zwh.getSelectedItem().toString());
                    u.setRecomm(" ");
                    db.SignUpUser(u);
                    frame.setContentPane(new Login_GUI(frame, db).pnl_Login);
                    frame.pack();
                    frame.setBounds(frame.getX(), frame.getY(), 600, 450);
                }
                else if(!flag){
                    JOptionPane.showMessageDialog(frame, "用户名已存在","错误",JOptionPane.ERROR_MESSAGE);
                }
                else if(password.equals("")){
                    JOptionPane.showMessageDialog(frame, "必须输入密码","错误",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "注册失败","错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btn_Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tF_Username.setText("");
                tF_Password.setText("");
                tF_Name.setText("");
                tF_Address.setText("");
                tF_Recom.setText("");
                tF_Telnum.setText("");
            }
        });
        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Login_GUI(frame, db).pnl_Login);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
    }
}
