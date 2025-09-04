package io.github.aks.exception;


public class ConnectionRefusedException extends Exception {
    public ConnectionRefusedException() {
        super("Connection refused, check the IP and Port");
    }
}
