package gui;

import system.Database;
import system.Sln;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;


public class prvtSln_Mng_page {
    public JPanel pnl_prvtSln_Mng_page;
    private JPanel pnl_Title;
    private JButton btn_SignOut;
    private JLabel lbl_Title;
    private JTable tbl_Slns;
    private JButton btn_Delete;
    private JButton btn_return;
    private JButton btn_Print;

    private JFrame frame;
    private String authorization;
    private Database db;
    private String username;
    private ArrayList<Sln> SlnArrayList;
    private DefaultTableModel model;

    public prvtSln_Mng_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;
        //ArrayList<Sln> SlnArrayList = new ArrayList<Sln>();
        SlnArrayList = db.CheckPrvtSln(username);
        Object[][] datas = {};
        String[] titles = {"id", "标题", "截止时间", "提案状态"};
        model = new DefaultTableModel(datas, titles);

        for(Sln s : SlnArrayList){
            model.addRow(new Object[] {Integer.toString(s.getId()), s.getName(), s.getDeadline(), s.getStatus()});
        }
        tbl_Slns.setModel(model);

        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Chose_Fuc_page(frame, authorization, username, db).Chose_Fuc_page);
                frame.pack();
                frame.setBounds(frame.getX(), frame.getY(), 600, 450);
            }
        });
        btn_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tbl_Slns.getRowCount(); i++){
                    if(tbl_Slns.isRowSelected(i)){
                        db.deleteSln(Integer.parseInt(tbl_Slns.getValueAt(i,0).toString()));
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
    }
}
