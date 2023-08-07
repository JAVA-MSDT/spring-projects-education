package com.javamsdt.song.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class SongUtil {

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(AppConstants.DOT));
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(AppConstants.DOT));
    }

    public static boolean checkFileExtension(MultipartFile file, String extension) {
        return getFileExtension
                (Objects.requireNonNull(file.getOriginalFilename()))
                .equalsIgnoreCase(extension);
    }

    public static String getMp3Extension(String audioCompressor) {
        if (audioCompressor != null && !audioCompressor.isEmpty()) {
            return audioCompressor.toLowerCase();
        } else {
            return "mp3";
        }
    }

    public static String getTitle(String title) {
        return title == null ? "song" : title;
    }

    public static Resource fileSystemResource(MultipartFile multipartSong, File file) throws IOException {
        multipartSong.transferTo(file);
        return new FileSystemResource(file);
    }
}
