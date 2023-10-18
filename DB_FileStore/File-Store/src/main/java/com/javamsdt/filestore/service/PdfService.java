package com.javamsdt.filestore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.filestore.dto.PdfDto;
import com.javamsdt.filestore.mapper.PdfMapper;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.model.Pdf;
import com.javamsdt.filestore.repository.PdfRepository;
import com.javamsdt.filestore.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
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

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PdfRepository pdfRepository;

    private final PdfMapper pdfMapper;

    @Value("${app.api.pdf}")
    private String pdfApi;

    public PdfDto savePdf(MultipartFile multipartPdf, HttpServletRequest request) throws Exception {
        String originalFilename = multipartPdf.getOriginalFilename();
        Pdf pdf = new Pdf();
        pdf.setName(FileStoreUtil.getFileNameWithoutExtension(originalFilename));
        pdf.setExtension(FileStoreUtil.getFileExtension(originalFilename, "pdf"));
        pdf.setContentType(FileStoreUtil.getContentType(originalFilename));
        pdf.setContent(multipartPdf.getBytes());
        pdf.setUrl(pdfApi);
        return pdfMapper.toPdfDto(pdfRepository.save(pdf));
    }

    public PdfDto findPdfById(Long id) {
        return pdfMapper.toFullPdfDto(getPdfById(id));
    }
    public ResponseEntity<Resource> getPdfResourceById(Long id) {
        Pdf pdf = getPdfById(id);
        ByteArrayResource resource = new ByteArrayResource(pdf.getContent());
        String fileNameHeader = "attachment; filename=" + pdf.getName() + "." + pdf.getExtension();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileNameHeader)
                .contentLength(pdf.getContent().length)
                .contentType(MediaType.parseMediaType(pdf.getContentType()))
                .body(resource);
    }

    public List<PdfDto> findAllPdfs() {
        return pdfRepository.findAll()
                .stream()
                .map(pdfMapper::toPdfDto)
                .collect(Collectors.toList());
    }

    public List<PdfDto> findByPdfIds(List<Long> ids) {
        return pdfRepository.findByPdfIds(ids)
                .stream()
                .map(pdfMapper::toPdfDto)
                .collect(Collectors.toList());
    }

    public PdfDto findPdfByName(String name) {
        Pdf pdf = pdfRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return pdfMapper.toFullPdfDto(pdf);
    }

    private Pdf getPdfById(Long pdfId) {
        return pdfRepository.findById(pdfId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}
