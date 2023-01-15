package com.javamsdt.filestore.repository;

import com.javamsdt.filestore.model.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
  Optional<Image> findByName(String name);

  @Query( "select i from Image i where id in :ids" )
  List<Image> findByImageIds(@Param("ids") List<Long> id);
}
