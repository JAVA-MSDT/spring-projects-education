package com.javamsdt.resource.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ResourceUtil {
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(AppConstants.DOT));
    }

    public static Metadata getResourceMetadata(MultipartFile song) {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        try (InputStream inputStream = song.getInputStream()) {
            Mp3Parser mp3Parser = new Mp3Parser();
            mp3Parser.parse(inputStream, handler, metadata, context);
        } catch (TikaException e) {
            log.error("TikaException while extracting the Metadata of:: " + song.getName(), e);
        } catch (IOException e) {
            log.error("IOException while extracting the Metadata of:: " + song.getName(), e);
        } catch (SAXException e) {
            log.error("SAXException while extracting the Metadata of:: " + song.getName(), e);
        }
        return metadata;
    }

}
