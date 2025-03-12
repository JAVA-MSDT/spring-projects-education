package com.javamsdt.filestore.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceCompressor {

    public static byte[] compress(byte[] data) {

        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("IOException while Compressing the resource ", e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompress(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (IOException e) {
            log.error("IOException while Decompressing the resource ", e);
        } catch (DataFormatException e) {
            log.error("DataFormatException while Decompressing the resource ", e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] compressBytes(byte[] data) {
        byte[] compressed = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
            compressed = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error during compressing data...");
        }
        return compressed;
    }

    public static byte[] decompressBytes(byte[] data) {
        byte[] decompressed = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            decompressed = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error during deCompressing data...", e);
        }
        return decompressed;
    }


}
