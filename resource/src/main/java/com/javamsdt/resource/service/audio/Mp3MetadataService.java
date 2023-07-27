package com.javamsdt.resource.service.audio;

import com.javamsdt.resource.mapping.Mp3MetadataMapper;
import com.javamsdt.resource.model.audio.Mp3Metadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mp3MetadataService {

    private final Mp3MetadataMapper mp3MetadataMapper;

    public Mp3Metadata getMp3Metadata(InputStream inputStream) {
        Metadata mp3Metadata = getResourceMetadataFromInputStream(inputStream);
        return mp3MetadataMapper.mapMp3Metadata(mp3Metadata);
    }

    public Metadata getResourceMetadataFromInputStream(InputStream inputStream) {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        try {
            Parser parser = new AutoDetectParser();
            parser.parse(inputStream, handler, metadata, context);
        } catch (TikaException e) {
            log.error("TikaException while extracting the Metadata ", e);
        } catch (IOException e) {
            log.error("IOException while extracting the Metadata ", e);
        } catch (SAXException e) {
            log.error("SAXException while extracting the Metadata ", e);
        }
        return metadata;
    }

    public Metadata extractMetadata(InputStream stream)
            throws IOException, SAXException, TikaException {

        Parser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
//        String contentType = detectDocType(stream);
//        System.out.println("contentType:: " + contentType);
        parser.parse(stream, handler, metadata, context);
        return metadata;
    }
}
