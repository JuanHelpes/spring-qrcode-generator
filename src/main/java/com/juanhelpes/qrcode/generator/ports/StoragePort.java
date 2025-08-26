package com.juanhelpes.qrcode.generator.ports;

public interface StoragePort {
    String uploadFile(String path, byte[] fileData, String contentType);
}
