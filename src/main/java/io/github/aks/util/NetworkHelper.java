package io.github.aks.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkHelper {
    public static String getLocalHost() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        String ip = inet.getHostAddress();
        return ip.substring(0, ip.lastIndexOf("."));
    }
}
