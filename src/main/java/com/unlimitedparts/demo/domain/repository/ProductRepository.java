package com.unlimitedparts.demo.domain.repository;

import com.unlimitedparts.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(List<Long> productIds);
    void deleteProductById(Long id);
    Product getProductById(Long id);
    Product getProductBySerialNumber(String serialNumber);
}
