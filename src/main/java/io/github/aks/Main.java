package io.github.aks;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String serverIP = "192.168.1.8";
        int port = 5000;

        try(Socket socket = new Socket(serverIP, port)){
            System.out.println("connected ...");
            
            OutputStream os = socket.getOutputStream();
            FileInputStream fis = new FileInputStream("src\\main\\resources\\canute.jpg");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(buffer)) != -1){
                os.write(buffer, 0 , bytesRead);
            }

            fis.close();
            socket.close();
            System.out.println("file sent .. ");
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}