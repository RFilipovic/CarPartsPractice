package com.unlimitedparts.crozzadatak.rest;
import com.unlimitedparts.crozzadatak.DTO.BrandDTO;
import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.request.CreateBrandRequest;
import com.unlimitedparts.crozzadatak.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car-brand")
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
        BrandDTO dto = brandService.getBrandById(brandId);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

}
