package io.github.aks.util;

import io.github.aks.client.ClientConnection;
import io.github.aks.header.Header;

import java.io.*;

public class FileSender {
    public static void sendFile(File file, OutputStream os) throws IOException {
        try(FileInputStream fis = new FileInputStream(file)){
            byte[] data = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(data)) != -1){
                os.write(data, 0, bytesRead);
            }
        }
    }
    public static void sendDir(String base, File file, ClientConnection connection) throws IOException{

        if(file.isDirectory()){
            Header header = new Header("DIR_UPLOAD", base + "/" + file.getName(), 0, "DISK");
            connection.sendHeader(header);
            System.out.println("shoulda sent the header " + base + file.getName());
        }
    }
}
