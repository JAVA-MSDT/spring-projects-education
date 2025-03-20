package com.javamsdt.productstore.repository;

import java.util.List;

import com.javamsdt.productstore.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i FROM Image i JOIN i.user iu WHERE iu.userId = :userId")
    List<Image> findImagesByUserId(@Param("userId") Long userId);
}
