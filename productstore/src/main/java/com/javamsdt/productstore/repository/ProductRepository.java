package com.javamsdt.productstore.repository;

import java.util.List;

import com.javamsdt.productstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN p.users pu WHERE pu.userId = :userId")
    List<Product> findUserProductsByUserId(@Param("userId") Long userId);
}
