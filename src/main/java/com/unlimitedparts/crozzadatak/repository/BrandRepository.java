package com.unlimitedparts.crozzadatak.repository;

import com.unlimitedparts.crozzadatak.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findById(Long id);
    void deleteById(Long id);
    Brand getBrandById(Long id);
}
