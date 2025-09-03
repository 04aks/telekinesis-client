package io.github.aks;
import io.github.aks.calls.Call;
import io.github.aks.calls.UploadFileCall;
import io.github.aks.client.ClientConnection;
import io.github.aks.config.ClientConfig;
import io.github.aks.network.PingSweep;
import io.github.aks.ui.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        new MainFrame("192.168.1", 200);
    }
}