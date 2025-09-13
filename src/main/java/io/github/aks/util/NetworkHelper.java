package io.github.aks.util;

import io.github.aks.Main;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkHelper {
    public static String getLocalHost() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        String ip = inet.getHostAddress();
        String subnet = ip.substring(0, ip.lastIndexOf("."));
        Main.SUBNET = subnet;
        return subnet;
    }
}
