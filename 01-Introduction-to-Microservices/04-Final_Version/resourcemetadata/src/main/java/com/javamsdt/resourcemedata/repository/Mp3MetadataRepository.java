package com.javamsdt.resourcemedata.repository;

import com.javamsdt.resourcemedata.model.Mp3Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Mp3MetadataRepository extends JpaRepository<Mp3Metadata, Long> {
    Optional<Mp3Metadata> findByResourceId(Long resourceId);
}
