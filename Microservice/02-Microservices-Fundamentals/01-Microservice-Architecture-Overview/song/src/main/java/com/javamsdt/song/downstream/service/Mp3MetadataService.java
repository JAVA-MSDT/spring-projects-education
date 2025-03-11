package com.javamsdt.song.downstream.service;

import com.javamsdt.song.downstream.mapping.Mp3MetadataMapper;
import com.javamsdt.song.downstream.model.Mp3Metadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mp3MetadataService {

    private final Mp3MetadataMapper mp3MetadataMapper;

    public Mp3Metadata getMp3Metadata(InputStream inputStream, Long songId) {
        Metadata mp3Metadata = getResourceMetadataFromInputStream(inputStream);
        return mp3MetadataMapper.mapMp3Metadata(mp3Metadata, songId);
    }

    public Metadata getResourceMetadataFromInputStream(InputStream inputStream) {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        try {
            Mp3Parser mp3Parser = new Mp3Parser();
            mp3Parser.parse(inputStream, handler, metadata, context);
        } catch (TikaException e) {
            log.error("TikaException while extracting the Metadata ", e);
        } catch (IOException e) {
            log.error("IOException while extracting the Metadata ", e);
        } catch (SAXException e) {
            log.error("SAXException while extracting the Metadata ", e);
        }
        return metadata;
    }
}
