package com.javamsdt.filestore.mapper;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(ignore = true, target = "content")
    ImageDto toImageDto(Image image);

}
