package com.javamsdt.filestore.service;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.mapper.ImageMapper;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.repository.ImageRepository;
import com.javamsdt.filestore.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    @Value("${images.folder}")
    private String imagesFolder;

    public ImageDto saveImage(MultipartFile multipartImage, String alt) throws IOException {
        String originalFilename = multipartImage.getOriginalFilename();
        byte[] content = multipartImage.getBytes();
        String imageName = FileStoreUtil.saveFileToFolder(multipartImage, imagesFolder);
        Image image = new Image();
        image.setName(FileStoreUtil.getFileNameWithoutExtension(originalFilename));
        image.setExtension(FileStoreUtil.getFileExtension(originalFilename, "png"));
        image.setContent(content);
        image.setAlt(alt);
        image.setContentType(FileStoreUtil.getContentType(originalFilename));
        image.setUrl("/images/" + imageName);

        return imageMapper.toImageDto(imageRepository.save(image));
    }

    public ImageDto findImageById(Long id) {
        return imageMapper.toFullImageDto(getImageById(id));
    }

    public ResponseEntity<Resource> getImageResourceById(Long id) {
        Image image = getImageById(id);
        ByteArrayResource resource = new ByteArrayResource(image.getContent());
        String fileNameHeader = "attachment; filename=" + image.getName() + "." + image.getExtension();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileNameHeader)
                .contentLength(image.getContent().length)
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(resource);
    }

    public ResponseEntity<Resource> getImageResourceByName(String name) {
        Image image = getImageByName(name);
        ByteArrayResource resource = new ByteArrayResource(image.getContent());
        String fileNameHeader = "attachment; filename=" + image.getName() + "." + image.getExtension();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileNameHeader)
                .contentLength(image.getContent().length)
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(resource);
    }

    public List<ImageDto> findAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(imageMapper::toImageDto)
                .toList();
    }

    public List<ImageDto> findByImageIds(List<Long> ids) {
        return imageRepository.findByImageIds(ids)
                .stream()
                .map(imageMapper::toImageDto)
                .toList();
    }

    public ImageDto findImageByName(String name) {
        return imageMapper.toFullImageDto(getImageByName(name));
    }

    private Image getImageByName(String name) {
        return imageRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image by name not found, provided name: " + name));
    }

    private Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image by id not found, provided ID: " + id));
    }
}
