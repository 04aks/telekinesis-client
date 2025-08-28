package io.github.aks;
import io.github.aks.calls.Call;
import io.github.aks.calls.UploadFileCall;
import io.github.aks.client.ClientConnection;
import io.github.aks.config.ClientConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String serverIP = "192.168.1.8";
        int port = 5000;
        File file = new File("src\\main\\resources\\aot-red-swan.mp4");
        System.out.println(file.exists());

        ClientConfig config = new ClientConfig(serverIP, port);
        try(ClientConnection clientConnection = new ClientConnection(config)){

            Call upload = new UploadFileCall(file, clientConnection);
            upload.execute();
            System.out.println("Call ended");

        }catch(IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}