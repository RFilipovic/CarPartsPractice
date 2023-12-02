package com.unlimitedparts.demo.controller;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.exception.ApiRequestException;
import com.unlimitedparts.demo.service.DTO.ProductDTO;
import com.unlimitedparts.demo.service.ProductService;
import com.unlimitedparts.demo.service.SaleService;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if (saleRequest.getFrom() != null && saleRequest.getTo() != null && saleRequest.getPercentage() != null){
            saleService.addSale(saleRequest);
            return ResponseEntity.ok("Sale successfully added");
        }
        throw new ApiRequestException("Could not add sale.");
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequest productRequest){
        if (productRequest.getSerialNumber() != null && productRequest.getBasePrice() != null){
            productService.addProduct(productRequest);
            return ResponseEntity.ok("Product successfully added.");
        }
        throw new ApiRequestException("Could not add product.");
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
        throw new ApiRequestException("Could not remove sale.");
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            product.setCarPart(null);
            productService.deleteProductById(productId);
            return ResponseEntity.ok("Product successfully deleted.");
        }
        throw new ApiRequestException("Could not remove product from sale.");
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody CreateProductRequest productRequest){
        if (productRequest == null)
            throw new ApiRequestException("Illegal product request.");
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            if(productRequest.getBasePrice() != null && productRequest.getSerialNumber() != null) {
                if (productService.updateProduct(product, productRequest))
                    return ResponseEntity.ok("Product successfully updated.");
            }
        }
        throw new ApiRequestException("Could not update product base price.");
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
        ProductDTO productDTO = productService.getProductDTOById(productId);
        if (productDTO != null){
            return ResponseEntity.ok(productDTO);
        }
        throw new ApiRequestException("Could not find product.");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOS = productService.getAllProductDTOS();
        if(productDTOS != null) {
            if (!productDTOS.isEmpty()) {
                return ResponseEntity.ok(productDTOS);
            }
        }
        throw new ApiRequestException("There are no products available.");
    }

}
