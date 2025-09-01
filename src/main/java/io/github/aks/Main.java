package io.github.aks;
import io.github.aks.calls.Call;
import io.github.aks.calls.UploadFileCall;
import io.github.aks.client.ClientConnection;
import io.github.aks.config.ClientConfig;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        // DEFAULTS
        String serverIP = "192.168.1.8";
        int port = 5000;
        String filePath = "src\\main\\resources\\canute.jpg";

        // args  given
        if(args.length > 0) serverIP = args[0];
        if(args.length > 1) port = Integer.parseInt(args[1]);
        if(args.length > 2) filePath = args[2];

        File file = new File(filePath);
        if(! file.exists()) throw new IllegalArgumentException("File Does not Exist.");

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