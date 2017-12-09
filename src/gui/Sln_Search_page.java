package gui;

import system.Database;
import system.Sln;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sln_Search_page {
    private JTable tbl_Sln;
    private JTextField tF_id;
    private JButton btn_Search;
    private JButton btn_Return;
    private JButton btn_SignOut;
    private JLabel lbl_Title;
    private JLabel lbl1;
    public JPanel pnl_Sln_Search_page;
    private JPanel pnl_Title;
    private JPanel pnl2;
    private JPanel pnl1;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public Sln_Search_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        ArrayList<Sln> SlnArrayList = db.CheckAllSln();
        Object[][] datas = {};
        String[] titles = {"id", "标题", "截止时间", "提案状态"};
        DefaultTableModel model = new DefaultTableModel(datas, titles);

        for(Sln s : SlnArrayList){
            model.addRow(new Object[] {Integer.toString(s.getId()), s.getName(), s.getDeadline(), s.getStatus()});
        }
        tbl_Sln.setModel(model);


        btn_Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int id = Integer.parseInt(tF_id.getText());
                int id;
                try {
                    id = Integer.parseInt(tF_id.getText());
                    Sln s = db.CheckOneSln(id);
                    if (s.getId() == 0){
                        JOptionPane.showMessageDialog(frame, "不存在这个提案","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } catch (NumberFormatException n) {
                    // TODO Auto-generated catch block
                    tF_id.setText("");
                    JOptionPane.showMessageDialog(frame, "非法输入","错误",JOptionPane.ERROR_MESSAGE);
                    n.printStackTrace();
                    return;
                }
                frame.setContentPane(new DocView_page(frame, authorization, username, db, id).pnl_DocView_page);
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
