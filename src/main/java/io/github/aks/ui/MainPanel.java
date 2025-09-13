package io.github.aks.ui;

import io.github.aks.Main;
import io.github.aks.client.ItemUploader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class MainPanel extends JPanel implements ActionListener {
    private final JFrame frame;
    private JComboBox<String> hosts;
    private JButton sendButton;
    private JTextField portField;
    private JTextField filePathField;
    private JButton refreshButton;
    public  MainPanel(JFrame frame){
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(hostsSelector());
        makeSpace(this, 0, 5);
        add(port());
        makeSpace(this, 0, 5);
        add(selectFile());

        makeSpace(this, 0, 20);
        add(sendButton());
    }

    public JComboBox<String> getHosts() {
        return hosts;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JTextField getPortField() {
        return portField;
    }

    JComponent hostsSelector(){
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));

        hosts = new JComboBox<>(new String[]{"Scanning ..."});
        hosts.setSelectedIndex(0);

        refreshButton = new JButton("Refresh");
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(this);

        comboPanel.add(hosts);
        makeSpace(comboPanel, 5, 0);
        comboPanel.add(refreshButton);

        return comboPanel;
    }

    JComponent port(){
        String hint = "Port";
        portField = new JTextField(hint);
        portField.setForeground(Color.gray);
        portField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(portField.getText().equals(hint)){
                    portField.setText("");
                    portField.setForeground(Color.black);
                    portField.setEditable(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(portField.getText().isEmpty()){
                    portField.setText(hint);
                    portField.setForeground(Color.gray);
                }
            }
        });
        return portField;
    }

    JComponent selectFile(){
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
        filePathField = new JTextField();
//        filePathField.setEditable(false);
        JButton browseButton = new JButton("Browse");
        filePanel.add(filePathField);
        makeSpace(filePanel, 5, 0);
        filePanel.add(browseButton);
        chooseFile(browseButton, filePathField);
        return filePanel;
    }

    JComponent sendButton(){
        sendButton = new JButton("SEND");
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendButton.addActionListener(this);
        return sendButton;
    }

    void chooseFile(JButton button, JTextField textField){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        button.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                textField.setText(selectedFile.getAbsolutePath());
            }
        });
    }

    void makeSpace(JComponent target, int width, int height){
        target.add(Box.createRigidArea(new Dimension(width, height)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton){
            String ip = hosts.getSelectedItem().toString();
            int port = Integer.parseInt(portField.getText());
            File file = new File(filePathField.getText());

            ItemUploader itemUploader = new ItemUploader(ip, port);
            itemUploader.upload(file);
        }

        if(e.getSource() == refreshButton){
            new HostDiscoveryWorker(Main.SUBNET, Main.TIMEOUT, this).execute();
        }
    }
}
