package io.github.aks.ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String subnet, int timeout){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,200);

        MainPanel panel = new MainPanel(this);
        add(panel);
        new HostDiscoveryWorker(subnet, timeout, panel).execute();

        setResizable(false);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
