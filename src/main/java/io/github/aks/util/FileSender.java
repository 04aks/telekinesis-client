package io.github.aks.util;

import io.github.aks.client.ClientConnection;
import io.github.aks.header.Header;
import java.io.*;

public class FileSender {
    public static void sendFile(File file, ClientConnection connection) throws IOException {
        try(FileInputStream fis = new FileInputStream(file)){
            byte[] data = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(data)) != -1){
                connection.getDataOutputStream().write(data, 0, bytesRead);
            }
            connection.getDataOutputStream().flush();
        }
    }
    public static void sendDir(File file, ClientConnection connection) throws IOException{
        // shake on it for receiving the base dir
        String done = connection.readResponse();
        if(! "DONE".equals(done)) throw new IOException("Expected Ready but got: " + done);

        String base = file.getName();
        File[] files = file.listFiles();
        if(files == null) return;

        Header header;
        for(File f : files){
            if(f.isDirectory()){

                header = new Header("DIR_UPLOAD",
                        base + "/" + f.getName(),
                        f.length(),
                        "DISK");
                connection.sendHeader(header);

                // too overwhelmed at the minute for me to fix this on the server side
                // it is a feature anyway O_~
                String ready = connection.readResponse();
                if(! "READY".equals(ready)) throw new IOException("Expected Ready but got: " + ready);
            }
            else{

                header = new Header("FILE_UPLOAD",
                        base + "/" + f.getName().replaceAll(" ", "_"),
                        f.length(),
                        "DISK");
                connection.sendHeader(header);
                // forgot to send the file bruv smh ....
                String response = connection.readResponse();
                if(response.equals("READY")) {
                    sendFile(f, connection);
                }
            }

            String fileDone = connection.readResponse();
            if(! "DONE".equals(fileDone)) throw new IOException("Expected Done but got: " + fileDone);


        }
    }
}
