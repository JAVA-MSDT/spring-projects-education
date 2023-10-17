package com.javamsdt.filestore.repository;

import java.util.List;
import java.util.Optional;

import com.javamsdt.filestore.model.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Long> {
    Optional<Pdf> findByName(String name);

    @Query( "select p from Pdf p where id in :ids" )
    List<Pdf> findByPdfIds(@Param("ids") List<Long> id);
}
