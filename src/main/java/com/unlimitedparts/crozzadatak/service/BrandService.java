package com.unlimitedparts.crozzadatak.service;

import com.unlimitedparts.crozzadatak.DTO.BrandDTO;
import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.request.CreateBrandRequest;

public interface BrandService {
    Brand addBrand(CreateBrandRequest brandRequest);
    BrandDTO getBrandById(Long id);
}
