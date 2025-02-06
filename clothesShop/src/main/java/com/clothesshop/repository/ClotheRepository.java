package com.clothesshop.repository;

import com.clothesshop.model.clothe.Clothe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClotheRepository extends JpaRepository<Clothe, Long> {

    Page<Clothe> findByClotheTypeContainingIgnoreCase(String clotheType, Pageable pageable);

    Page<Clothe> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Page<Clothe> findByFabricContainingIgnoreCase(String description, Pageable pageable);

    Page<Clothe> findByGenderContainingIgnoreCase(String description, Pageable pageable);

    Page<Clothe> findBySizeContainingIgnoreCase(String description, Pageable pageable);
}
