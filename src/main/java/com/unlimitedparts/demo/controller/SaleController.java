package com.unlimitedparts.demo.controller;

import com.unlimitedparts.demo.service.ProductService;
import com.unlimitedparts.demo.service.SaleService;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;

    @Autowired
    public SaleController(SaleService saleService, ProductService productService){
        this.saleService = saleService;
        this.productService = productService;
    }

    @PostMapping("/sales")
    public ResponseEntity<String> addSale(@RequestBody CreateSaleRequest saleRequest){
        if (saleRequest != null){
            saleService.addSale(saleRequest);
            return ResponseEntity.ok("Sale successfully added");
        }
        return ResponseEntity.badRequest().body("Could not add sale.");
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequest productRequest){
        if (productRequest != null){
            productService.addProduct(productRequest);
            return ResponseEntity.ok("Product successfully added.");
        }
        return ResponseEntity.badRequest().body("Could not add product.");
    }
}
