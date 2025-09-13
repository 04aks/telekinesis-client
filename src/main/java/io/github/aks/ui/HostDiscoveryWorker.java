package io.github.aks.ui;

import io.github.aks.network.PingSweep;
import javax.swing.*;
import java.util.List;

public class HostDiscoveryWorker extends SwingWorker<List<String>, Void> {

    private final String subnet;
    private final int timeout;
    private final MainPanel panel;

    public HostDiscoveryWorker(String subnet, int timeout, MainPanel panel) {
        this.subnet = subnet;
        this.timeout = timeout;
        this.panel = panel;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        panel.getRefreshButton().setEnabled(false);
        panel.getHosts().setEnabled(false);
        return PingSweep.discover(subnet, timeout);
    }

    @Override
    protected void done() {
        try{
            List<String> hosts = get();
            panel.getHosts().removeAllItems();
            for(String host : hosts){
                panel.getHosts().addItem(host);
            }
            panel.getHosts().setEnabled(true);
            panel.getRefreshButton().setEnabled(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
