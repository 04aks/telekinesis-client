package io.github.aks.header;

public class Header {
    private final String type;
    private final String filename;
    private final long fileLength;
    private final String storageUnit;

    public Header(String type, String filename, long fileLength, String storageUnit) {
        this.type = type;
        this.filename = filename;
        this.fileLength = fileLength;
        this.storageUnit = storageUnit;
    }

    public String format(){
        return String.format("%s %s %d %s", type, filename, fileLength, storageUnit);
    }
}
