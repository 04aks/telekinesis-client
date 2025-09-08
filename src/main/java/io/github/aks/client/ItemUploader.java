package io.github.aks.client;

import io.github.aks.calls.Call;
import io.github.aks.calls.UploadDirCall;
import io.github.aks.calls.UploadFileCall;
import io.github.aks.config.ClientConfig;

import java.io.File;

public class ItemUploader {
    private final String serverIp;
    private final int port;

    public ItemUploader(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public void upload(File file){
        if(! file.exists()) throw new IllegalArgumentException("File does not exist!");

        ClientConfig config = new ClientConfig(serverIp, port);
        try(ClientConnection connection = new ClientConnection(config)){
            Call upload = !file.isDirectory() ? new UploadFileCall(file, connection):
                    new UploadDirCall(file, connection);
            upload.execute();
            System.out.println("Call ended.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
