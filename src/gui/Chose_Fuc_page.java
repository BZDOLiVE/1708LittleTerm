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

    public Chose_Fuc_page(JFrame _frame, String _authorization) {
        frame = _frame;
        authorization = _authorization;
        if(authorization.equals("writer")){
            btn_IdMng.setEnabled(false);
            btn_SlnMng.setEnabled(false);
            btn_SlnStart.setEnabled(false);
        }
        btn_SlnSrch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn_SlnWrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn_InfoUpdt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn_IdMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn_SlnMng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn_SlnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
