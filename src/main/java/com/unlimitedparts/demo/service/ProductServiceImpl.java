package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.domain.repository.ProductRepository;
import com.unlimitedparts.demo.domain.repository.SaleRepository;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SaleRepository saleRepository){
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Product addProduct(CreateProductRequest productRequest) {
        if (productRequest.getSerialNumber() == null || productRequest.getBasePrice() == null
        || productRequest.getSaleId() == null)
            throw new IllegalArgumentException("Invalid product request");

        Product product = new Product();
        product.setSerialNumber(productRequest.getSerialNumber());
        product.setBasePrice(productRequest.getBasePrice());
        Long saleId = productRequest.getSaleId();
        if (saleId != null){
            Sale sale = saleRepository.getSaleById(saleId);
            product.setSale(sale);
        }
        return productRepository.save(product);
    }
}
