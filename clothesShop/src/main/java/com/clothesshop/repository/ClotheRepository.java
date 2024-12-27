package com.clothesshop.repository;

import com.clothesshop.model.clothe.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClotheRepository extends JpaRepository<Clothe, Long> {
}
