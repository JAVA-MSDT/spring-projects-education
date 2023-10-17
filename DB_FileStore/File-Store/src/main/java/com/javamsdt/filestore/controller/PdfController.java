package com.javamsdt.filestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("${app.api.version}/pdfs")
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    @PostMapping
    PdfDto savePdf(@RequestParam("pdf") MultipartFile multipartPdf,HttpServletRequest request) throws Exception {
        return pdfService.savePdf(multipartPdf, request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    Resource findPdfById(@PathVariable("id") Long id) {
        byte[] pdf = pdfService.findPdfById(id)
                .getContent();
        return new ByteArrayResource(pdf);
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
    Resource findPdfByName(@PathVariable("name") String name) {
        byte[] pdf = pdfService.findPdfByName(name)
                .getContent();
        return new ByteArrayResource(pdf);
    }
}
