package com.javamsdt.resourceprocessor.service;

import com.javamsdt.resourceprocessor.mapping.Mp3MetadataMapper;
import com.javamsdt.resourceprocessor.model.Mp3Metadata;
import com.javamsdt.resourceprocessor.repository.Mp3MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mp3MetadataService {

    private final Mp3MetadataRepository mp3MetadataRepository;
    private final Mp3MetadataMapper mp3MetadataMapper;

    public Mp3Metadata getMp3MetadataByResourceId(Long resourceId) {
        return findByResourceId(resourceId);
    }

    public List<Mp3Metadata> getAllMp3Metadata() {
        return mp3MetadataRepository.findAll();
    }

    public Mp3Metadata saveMp3Metadata(MultipartFile mp3File, Long resourceId) {
        Metadata extractedMetadata = getResourceMetadataFromInputStream(mp3File);
        return mp3MetadataRepository.save(mp3MetadataMapper.mapMp3Metadata(extractedMetadata, resourceId));
    }

    public void deleteMp3MetadataByResourcesId(Long resourceId) {
        mp3MetadataRepository.delete(findByResourceId(resourceId));
    }

    private Mp3Metadata findByResourceId(Long resourceId) {
        return mp3MetadataRepository
                .findByResourceId(resourceId)
                .orElseThrow(() ->
                        getNotFoundResponseStatusException(String.format("Metadata with that resource id: %s is Not found in DB", resourceId)));
    }

    private ResponseStatusException getNotFoundResponseStatusException(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

    public Metadata getResourceMetadataFromInputStream(MultipartFile mp3File) {
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        try {
            Mp3Parser mp3Parser = new Mp3Parser();
            mp3Parser.parse(mp3File.getInputStream(), handler, metadata, context);
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
