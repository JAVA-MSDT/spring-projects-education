package com.javamsdt.resource.controller;

import com.javamsdt.resource.model.Resource;
import com.javamsdt.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.version}")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping
    public List<Resource> getResources() {
        return resourceService.getResources();
    }

    @GetMapping(value = "/extension/{extension}")
    public List<Resource> getResourcesByExtension(@PathVariable(name = "extension") String extension) {
        return resourceService.getResourcesByExtension(extension);
    }

    @GetMapping("/{id}")
    public Resource getResource(@PathVariable() Long id) {
        return resourceService.getResourceById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Resource> saveResource(@RequestBody() MultipartFile resource) throws IOException, TikaException, SAXException {
        return ResponseEntity.ok(resourceService.saveResource(resource));
    }

    @DeleteMapping("/delete-resources")
    public ResponseEntity<?> deleteResources(@RequestParam(name = "ids") Long[] ids) {
        return resourceService.deleteResources(ids);
    }
    @DeleteMapping("/delete-s3/{name}")
    public ResponseEntity<?> deleteResourcesFromS3(@PathVariable("name") String name) {
        resourceService.deleteResourceFromS3(name);
        return ResponseEntity.ok().build();
    }
}
