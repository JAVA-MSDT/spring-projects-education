package com.javamsdt.resource.service.image;

import com.javamsdt.resource.model.image.Image;
import com.javamsdt.resource.repository.ImageRepository;
import com.javamsdt.resource.util.ResourceCompressor;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image saveImage(MultipartFile imageFile) throws IOException {
        String imageOriginalName = imageFile.getOriginalFilename();
        Image imageToBeSaved = new Image();

        imageToBeSaved.setName(ResourceUtil.getFileName(Objects.requireNonNull(imageOriginalName)));
        imageToBeSaved.setExtension(ResourceUtil.getFileExtension(Objects.requireNonNull(imageOriginalName)));
        imageToBeSaved.setOriginalName(imageOriginalName);
        imageToBeSaved.setContentType(imageFile.getContentType());
        imageToBeSaved.setImage(ResourceCompressor.compressResource(imageFile.getBytes()));

        Image image = imageRepository.save(imageToBeSaved);
        image.setImage(ResourceCompressor.decompressResource(image.getImage()));
        return image;
    }

    public Image getImageById(Long id) {
        return findImageById(id);
    }

    public Image findImageByName(String name) {
        Image image = imageRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Image with that Name: %s is Not found in DB", name)));
        image.setImage(ResourceCompressor.decompressResource(image.getImage()));
        return image;
    }

    private Image findImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Image with that id: %s is Not found in DB", id)));
        image.setImage(ResourceCompressor.decompressResource(image.getImage()));
        return image;
    }
}
