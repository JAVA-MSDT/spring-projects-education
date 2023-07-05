package com.javamsdt.resource.metadata.mp3.service;

import com.javamsdt.resource.metadata.mp3.mapping.Mp3MetadataMapper;
import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mp3MetadataService {

    private final Mp3MetadataMapper mp3MetadataMapper;

    public Mp3Metadata getMp3Metadata(MultipartFile song, Long songId) {
        Metadata mp3Metadata = getResourceMetadata(song);
        return mp3MetadataMapper.mapMp3Metadata(mp3Metadata, songId);
    }

    private Metadata getResourceMetadata(MultipartFile song) {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        try (InputStream inputStream = song.getInputStream()) {
            Mp3Parser mp3Parser = new Mp3Parser();
            mp3Parser.parse(inputStream, handler, metadata, context);
        } catch (TikaException e) {
            log.error(String.format("TikaException while extracting the Metadata of %s ", song.getName()), e);
        } catch (IOException e) {
            log.error(String.format("IOException while extracting the Metadata of %s ", song.getName()), e);
        } catch (SAXException e) {
            log.error(String.format("SAXException while extracting the Metadata of %s ", song.getName()), e);
        }
        return metadata;
    }
}
