package io.github.aks.calls;

import io.github.aks.client.ClientConnection;
import io.github.aks.header.Header;
import io.github.aks.util.FileSender;
import java.io.File;
import java.io.IOException;

public class UploadFileCall implements Call{
    private final File file;
    private final ClientConnection connection;

    public UploadFileCall(File file, ClientConnection connection) {
        this.file = file;
        this.connection = connection;
    }

    @Override
    public void execute() throws IOException {
        Header header = new Header("FILE_UPLOAD", file.getName(), file.length(), "DISK");
        connection.sendHeader(header);

        String response = connection.readResponse();
        if(response.equals("READY")){
            FileSender.sendFile(file, connection);
        }
        else{
            throw new IOException("Unexpected response: " + response);
        }
    }
}
