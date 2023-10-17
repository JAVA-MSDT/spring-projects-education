package com.javamsdt.filestore.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.javamsdt.filestore.dto.PdfDto;
import com.javamsdt.filestore.mapper.PdfMapper;
import com.javamsdt.filestore.mapper.PdfMapper;
import com.javamsdt.filestore.model.Pdf;
import com.javamsdt.filestore.repository.PdfRepository;
import com.javamsdt.filestore.repository.PdfRepository;
import com.javamsdt.filestore.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PdfRepository pdfRepository;

    private final PdfMapper pdfMapper;

    public PdfDto savePdf(MultipartFile multipartPdf, HttpServletRequest request) throws Exception {
        Pdf pdf = new Pdf();
        pdf.setName(
                FileStoreUtil.extractFileName(Objects.requireNonNull(multipartPdf.getOriginalFilename())).get(0));
        pdf.setExtension(
                FileStoreUtil.extractFileName(Objects.requireNonNull(multipartPdf.getOriginalFilename())).get(1));
        pdf.setContent(multipartPdf.getBytes());
        Pdf dbPdf = pdfRepository.save(pdf);
        return pdfMapper.toPdfDto(
                updatePdfLocation(dbPdf.getId(), FileStoreUtil.buildFileUrl(request, dbPdf.getId())));
    }

    public PdfDto findPdfById(Long id) {
        return pdfMapper.toFullPdfDto(getPdfById(id));
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

    public Pdf findPdfByName(String name) {
        return pdfRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Pdf updatePdfLocation(Long id, String location) {
        Pdf dbPdf = getPdfById(id);
        dbPdf.setLocation(location);
        return pdfRepository.save(dbPdf);
    }

    private Pdf getPdfById(Long pdfId) {
        return pdfRepository.findById(pdfId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
