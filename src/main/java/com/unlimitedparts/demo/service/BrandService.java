package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.service.DTO.BrandDTO;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.service.request.CreateBrandRequest;

public interface BrandService {
    Brand addBrand(CreateBrandRequest brandRequest);
    BrandDTO getBrandById(Long id);
}
