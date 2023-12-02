package com.unlimitedparts.demo.controller;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.exception.ApiRequestException;
import com.unlimitedparts.demo.service.DTO.BrandDTO;
import com.unlimitedparts.demo.service.request.CreateBrandRequest;
import com.unlimitedparts.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/warehouse/car-brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<String> addBrand(@RequestBody CreateBrandRequest brandRequest){
        if (brandRequest.getBrandName() != null){
            brandService.addBrand(brandRequest);
            return ResponseEntity.ok("Successfully added brand.");
        }
        throw new ApiRequestException("Could not add car-brand to the database.");
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long brandId){
        BrandDTO dto = brandService.getBrandDTOById(brandId);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }
        throw new ApiRequestException("Could not return car-brand from the database.");
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<String> deleteBrandById(@PathVariable Long brandId){
        Optional<Brand> brandOptional = brandService.getBrandById(brandId);
        if (brandOptional.isPresent()){
            Brand brand = brandOptional.get();
            if(!brand.getCars().isEmpty()) throw new ApiRequestException("Could not delete car-brand from the database because it is still linked to the cars.");
            brand.getCars().clear();
            brandService.deleteBrandById(brandId);
            return ResponseEntity.ok("Brand successfully deleted.");
        }
        throw new ApiRequestException("Could not delete car-brand from the database.");
    }

}
