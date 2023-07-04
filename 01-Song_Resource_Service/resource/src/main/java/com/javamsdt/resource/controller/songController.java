package com.javamsdt.resource.controller;

import com.javamsdt.resource.model.Song;
import com.javamsdt.resource.service.SongService;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${api.version}/songs")
@RequiredArgsConstructor
public class songController {
    private final SongService songService;

    @GetMapping
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable() Long id) {
        return songService.getSong(id);
    }

    @GetMapping(value = "/song/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    Resource getSongContentById(@PathVariable("id") Long id) {
        return new ByteArrayResource(songService.getSong(id).getSong());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Long saveSong(@RequestBody() MultipartFile song) throws IOException, TikaException, SAXException {
        return songService.saveMultipartSong(song).getId();
    }

    private void mp3(MultipartFile song) throws IOException, TikaException, SAXException {
        System.out.println("Name:: " + song.getName());
        System.out.println("getContentType:: " + song.getContentType());
        System.out.println("getOriginalFilename:: " + song.getOriginalFilename());
        System.out.println("Extension:: " + ResourceUtil.getFileExtension(Objects.requireNonNull(song.getOriginalFilename())));

        InputStream inputStream = song.getInputStream();
        System.out.println("Available Stream:: " + inputStream.available());
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler();
        ParseContext context = new ParseContext();

        Mp3Parser mp3Parser = new Mp3Parser();
        mp3Parser.parse(inputStream, handler, metadata, context);

        String[] metaString = metadata.names();

        for (String meta : metaString) {
            System.out.println("Meta Key:: " + meta);
            System.out.println("Meta Value:: " + metadata.get(meta));
            System.out.println("====================");
        }
    }

}
