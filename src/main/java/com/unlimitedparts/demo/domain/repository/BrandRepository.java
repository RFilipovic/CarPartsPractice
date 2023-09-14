package com.unlimitedparts.demo.domain.repository;

import com.unlimitedparts.demo.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    void deleteBrandById(Long id);
    Brand getBrandById(Long id);
}
