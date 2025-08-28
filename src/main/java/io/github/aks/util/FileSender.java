package io.github.aks.util;

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
}
