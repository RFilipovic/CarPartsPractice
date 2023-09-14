package com.unlimitedparts.demo.controller;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.service.BrandServiceImpl;
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
        if (brandRequest != null){
            brandService.addBrand(brandRequest);
            return ResponseEntity.ok("Successfully added brand.");
        }
        return ResponseEntity.badRequest().body("Could not add brand.");
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long brandId){
        BrandDTO dto = brandService.getBrandDTOById(brandId);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<String> deleteBrandById(@PathVariable Long brandId){
        Optional<Brand> brandOptional = brandService.getBrandById(brandId);
        if (brandOptional.isPresent()){
            Brand brand = brandOptional.get();
            brand.getCars().clear();
            brandService.deleteBrandById(brandId);
            return ResponseEntity.ok("Brand successfully deleted.");
        }
        return ResponseEntity.notFound().build();
    }

}
