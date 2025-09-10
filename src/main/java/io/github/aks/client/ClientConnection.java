package io.github.aks.client;

import io.github.aks.config.ClientConfig;
import io.github.aks.exception.ConnectionRefusedException;
import io.github.aks.header.Header;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection implements AutoCloseable{
    private final Socket socket;
    private final DataOutputStream dos;
    private final DataInputStream dis;

    public ClientConnection(ClientConfig config) throws ConnectionRefusedException {
        try{
            this.socket = new Socket(config.serverIp(), config.port());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.dis = new DataInputStream(socket.getInputStream());

        }catch(IOException e){
            throw new ConnectionRefusedException();
        }
    }
    public void write(String msg) throws IOException{
        dos.writeUTF(msg);
    }

    public void sendHeader(Header header) throws IOException{
        dos.writeUTF(header.format());
    }

    public String readResponse() throws IOException{
        return dis.readUTF();
    }

    public DataOutputStream getDataOutputStream() {
        return dos;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
