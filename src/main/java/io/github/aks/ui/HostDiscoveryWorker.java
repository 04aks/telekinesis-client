package io.github.aks.ui;

import io.github.aks.network.PingSweep;
import javax.swing.*;
import java.util.List;

public class HostDiscoveryWorker extends SwingWorker<List<String>, Void> {

    private final String subnet;
    private final int timeout;
    private final JComboBox<String>  comboBox;

    public HostDiscoveryWorker(String subnet, int timeout, JComboBox<String> comboBox) {
        this.subnet = subnet;
        this.timeout = timeout;
        this.comboBox = comboBox;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        return PingSweep.discover(subnet, timeout);
    }

    @Override
    protected void done() {
        try{
            List<String> hosts = get();
            comboBox.removeAllItems();
            for(String host : hosts){
                comboBox.addItem(host);
            }
            comboBox.enable();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
