package io.github.aks;
import io.github.aks.ui.MainFrame;
import io.github.aks.util.NetworkHelper;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {

        new MainFrame(NetworkHelper.getLocalHost(), 200);
    }
}