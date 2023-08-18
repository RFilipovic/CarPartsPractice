package com.unlimitedparts.crozzadatak.repository;

import com.unlimitedparts.crozzadatak.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getBrandById(Long id);
}