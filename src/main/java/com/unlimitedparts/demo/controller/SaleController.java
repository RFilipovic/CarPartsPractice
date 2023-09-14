package com.unlimitedparts.demo.controller;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.service.ProductService;
import com.unlimitedparts.demo.service.SaleService;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping("/sales/{saleId}")
    public ResponseEntity<String> deleteSaleById(@PathVariable Long saleId){
        Optional<Sale> saleOptional = saleService.getSaleById(saleId);
        if (saleOptional.isPresent()){
            Sale sale = saleOptional.get();

            for (Product product : sale.getProducts()){
                product.setSale(null);
            }

            sale.getProducts().clear();
            saleService.deleteSaleById(saleId);
            return ResponseEntity.ok("Sale successfully deleted.");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequest productRequest){
        if (productRequest != null){
            productService.addProduct(productRequest);
            return ResponseEntity.ok("Product successfully added.");
        }
        return ResponseEntity.badRequest().body("Could not add product.");
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()){
            productService.deleteProductById(productId);
            return ResponseEntity.ok("Product successfully deleted.");
        }
        return ResponseEntity.badRequest().build();
    }
}
