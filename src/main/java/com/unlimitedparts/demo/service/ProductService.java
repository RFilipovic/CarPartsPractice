package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import java.util.Optional;

public interface ProductService {

    void addProduct(CreateProductRequest productRequest);
    Optional<Product> getProductById(Long id);
    void deleteProductById(Long id);

}
