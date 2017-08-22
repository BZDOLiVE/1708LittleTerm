package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chose_Fuc_page {
    public JPanel Chose_Fuc_page;
    private JButton btn_SlnSrch;
    private JButton btn_SlnWrt;
    private JButton btn_InfoUpdt;
    private JButton btn_IdMng;
    private JButton btn_SlnMng;
    private JButton btn_SlnStart;

    private JFrame frame;
    private String authorization;

    public Chose_Fuc_page(JFrame _frame, String authorization) {
        frame = _frame;
        btn_SlnSrch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
