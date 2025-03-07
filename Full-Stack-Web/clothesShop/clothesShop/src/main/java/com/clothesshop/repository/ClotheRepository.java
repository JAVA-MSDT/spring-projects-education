package com.clothesshop.repository;

import com.clothesshop.model.clothe.Clothe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClotheRepository extends JpaRepository<Clothe, Long> {

    Page<Clothe> findByCategoryContainingIgnoreCase(String category, Pageable pageable);

    Page<Clothe> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Page<Clothe> findByFabricContainingIgnoreCase(String fabric, Pageable pageable);

    Page<Clothe> findByGenderContainingIgnoreCase(String gender, Pageable pageable);

    Page<Clothe> findBySizeContainingIgnoreCase(String size, Pageable pageable);
}
