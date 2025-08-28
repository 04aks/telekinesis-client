package io.github.aks.client;

import io.github.aks.config.ClientConfig;
import io.github.aks.header.Header;
import java.io.*;
import java.net.Socket;

public class ClientConnection implements AutoCloseable{
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final OutputStream os;

    public ClientConnection(ClientConfig config) throws IOException {
        this.socket = new Socket(config.serverIp(), config.port());
        this.os = socket.getOutputStream();
        this.writer = new PrintWriter(os, true);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendHeader(Header header){
        writer.println(header.format());
    }

    public String readResponse() throws IOException{
        return reader.readLine();
    }

    public OutputStream getOutputStream() {
        return os;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
