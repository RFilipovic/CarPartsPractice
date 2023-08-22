package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.DTO.BrandDTO;
import com.unlimitedparts.demo.model.Brand;
import com.unlimitedparts.demo.request.CreateBrandRequest;

public interface BrandService {
    Brand addBrand(CreateBrandRequest brandRequest);
    BrandDTO getBrandById(Long id);
}
