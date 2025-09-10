package io.github.aks.calls;

import io.github.aks.client.ClientConnection;
import io.github.aks.header.Header;
import io.github.aks.util.FileSender;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UploadDirCall implements Call{
    private final ClientConnection connection;
    private final File file;

    public UploadDirCall(File file, ClientConnection connection) {
        this.connection = connection;
        this.file = file;
    }

    @Override
    public void execute() throws IOException {

        Header header = new Header("DIR_UPLOAD",
                file.getName().replaceAll(" ", "_"),
                file.length(),
                "DISK");

        connection.sendHeader(header);

        String response = connection.readResponse();
        if(response.equals("READY")) FileSender.sendDir(file, connection);
        else throw new IOException("Unexpected response: " + response);
    }
}
