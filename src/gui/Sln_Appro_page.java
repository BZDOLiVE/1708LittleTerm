package gui;

import system.Database;
import system.Sln;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sln_Appro_page {
    private JTable tbl_Sln;
    private JButton btn_Recomm;
    private JButton btn_Return;
    private JButton btn_SignOut;
    public JPanel pnl_Sln_Appro;

    private JFrame frame;
    private Database db;
    private String authorization;
    private String username;

    public Sln_Appro_page(JFrame _frame, String _authorization, String _username, Database _db) {
        frame = _frame;
        authorization = _authorization;
        username = _username;
        db = _db;

        ArrayList<Sln> SlnArrayList = db.CheckUnApproSln();
        Object[][] datas = {};
        String[] titles = {"id", "标题", "截止时间", "同意数", "不同意数"};
        DefaultTableModel model = new DefaultTableModel(datas, titles);

        for(Sln s : SlnArrayList){
            model.addRow(new Object[] {Integer.toString(s.getId()), s.getName(), s.getDeadline(), Integer.toString(s.getAgree()), Integer.toString(s.getDisagree())});
        }
        tbl_Sln.setModel(model);

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
                for(int i = 0; i < tbl_Sln.getRowCount(); i++){
                    if(tbl_Sln.isRowSelected(i)){
                        int id = Integer.parseInt(tbl_Sln.getValueAt(i,0).toString());
                        frame.setContentPane(new DocView_page(frame, authorization, username, db, id).pnl_DocView_page);
                        frame.pack();
                        frame.setBounds(frame.getX(), frame.getY(), 600, 450);
                    }

                }
            }
        });
    }
}
