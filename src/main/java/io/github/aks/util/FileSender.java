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
    public static void sendDir(File file, ClientConnection connection) throws IOException{
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
            }
            else{

                header = new Header("FILE_UPLOAD",
                        base + "/" + f.getName().replaceAll(" ", "_"),
                        f.length(),
                        "DISK");
            }

            connection.sendHeader(header);
        }
    }
}
