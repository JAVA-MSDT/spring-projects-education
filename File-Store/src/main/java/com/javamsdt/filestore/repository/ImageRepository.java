package com.javamsdt.filestore.repository;

import com.javamsdt.filestore.model.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
  Optional<Image> findByName(String name);
}
