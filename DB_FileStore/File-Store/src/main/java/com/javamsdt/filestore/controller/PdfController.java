package com.javamsdt.filestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.dto.PdfDto;
import com.javamsdt.filestore.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${app.api.pdf}")
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    @PostMapping
    PdfDto savePdf(@RequestParam("pdf") MultipartFile multipartPdf, HttpServletRequest request) throws Exception {
        return pdfService.savePdf(multipartPdf, request);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Resource> findPdfById(@PathVariable("id") Long id) {
        PdfDto pdfDto = pdfService.findPdfById(id);
        byte[] pdf = pdfDto.getContent();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(pdfDto.getContentType()))
                .body(new ByteArrayResource(pdf));
    }

    @GetMapping("/download-by-id/{id}")
    public ResponseEntity<Resource> downloadImageById(@PathVariable Long id) {
        return pdfService.getPdfResourceById(id);
    }

    @GetMapping
    ResponseEntity<List<PdfDto>> findAllPdfs() {
        return new ResponseEntity<>(pdfService.findAllPdfs(), HttpStatus.OK);
    }

    @GetMapping("/ids")
    ResponseEntity<List<PdfDto>> findPdfsByIds(@RequestParam("ids") List<Long> ids) {
        return new ResponseEntity<>(pdfService.findByPdfIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<Resource> findPdfByName(@PathVariable("name") String name) {
        PdfDto pdf = pdfService.findPdfByName(name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(pdf.getContentType()))
                .body(new ByteArrayResource(pdf.getContent()));
    }
}
