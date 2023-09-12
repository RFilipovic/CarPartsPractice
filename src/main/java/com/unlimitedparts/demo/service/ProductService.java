package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.service.request.CreateProductRequest;

public interface ProductService {

    Product addProduct(CreateProductRequest productRequest);
}
