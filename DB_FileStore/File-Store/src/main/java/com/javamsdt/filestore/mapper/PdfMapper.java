package com.javamsdt.filestore.mapper;

import com.javamsdt.filestore.dto.PdfDto;
import com.javamsdt.filestore.model.Pdf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PdfMapper {

    @Mapping(ignore = true, target = "content")
    PdfDto toPdfDto(Pdf pdf);

    PdfDto toFullPdfDto(Pdf pdf);

}
