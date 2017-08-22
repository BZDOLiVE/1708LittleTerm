package gui;

import system.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_GUI {

    private JTextField tF_Username;
    private JTextField tF_Password;
    private JButton btn_SignIn;
    private JButton btn_SignUp;
    private JPanel pnl_Login;
    private JPanel pnl_Input;
    private JLabel lbl_Username;
    private JLabel lbl_Password;
    private JPanel pnl_Button;
    private JScrollPane sclpnl_Information;
    private JPanel pnl_Title;
    private JLabel lbl_Title;

    private JFrame frame;
    private Database db;

    public Login_GUI(JFrame _frame, Database _db) {
        frame = _frame;
        db = _db;
        btn_SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tF_Username.getText();
                String password = tF_Password.getText();
                String authorization = db.SignInCheck(username, password);
                if(authorization.equals("admin")) {
                    System.out.println("登陆成功");
                    frame.setContentPane(new Chose_Fuc_page(frame, authorization).Chose_Fuc_page);
                    frame.pack();
                    frame.setBounds(200, 100, 600, 450);
                }
                else if(authorization.equals("writer")){
                    System.out.println("登陆成功");
                }
                else{
                    System.out.println("error");
                    JOptionPane.showMessageDialog(frame, "用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
                }



            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("文稿管理系统");
        Database db = new Database();
        frame.setContentPane(new Login_GUI(frame, db).pnl_Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(200, 100, 600, 450);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
