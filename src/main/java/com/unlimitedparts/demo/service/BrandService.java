package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.service.DTO.BrandDTO;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.service.request.CreateBrandRequest;

import java.util.Optional;

public interface BrandService {
    Brand addBrand(CreateBrandRequest brandRequest);
    BrandDTO getBrandDTOById(Long id);
    Optional<Brand> getBrandById(Long id);

    void deleteBrandById(Long brandId);
}
