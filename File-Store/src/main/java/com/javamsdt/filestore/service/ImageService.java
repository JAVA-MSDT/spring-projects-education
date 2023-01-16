package com.javamsdt.filestore.service;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.mapper.ImageMapper;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.repository.ImageRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.filestore.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    public ImageDto saveImage(MultipartFile multipartImage, String alt, HttpServletRequest request) throws Exception {
        Image image = new Image();
        image.setName(
                FileStoreUtil.extractFileName(Objects.requireNonNull(multipartImage.getOriginalFilename())).get(0));
        image.setExtension(
                FileStoreUtil.extractFileName(Objects.requireNonNull(multipartImage.getOriginalFilename())).get(1));
        image.setContent(multipartImage.getBytes());
        image.setAlt(alt);
        Image dbImage = imageRepository.save(image);
        return imageMapper.toImageDto(
                updateImageLocation(dbImage.getId(), FileStoreUtil.buildFileUrl(request, dbImage.getId())));
    }

    public ImageDto findImageById(Long id) {
        return imageMapper.toFullImageDto(getImageById(id));
    }
    public List<ImageDto> findAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(imageMapper::toImageDto)
                .collect(Collectors.toList());
    }

    public List<ImageDto> findByImageIds(List<Long> ids) {
        return imageRepository.findByImageIds(ids)
                .stream()
                .map(imageMapper::toImageDto)
                .collect(Collectors.toList());
    }

    public Image findImageByName(String name) {
        return imageRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Image updateImageLocation(Long id, String location) {
        Image dbImage = getImageById(id);
        dbImage.setLocation(location);
        return imageRepository.save(dbImage);
    }

    private Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
