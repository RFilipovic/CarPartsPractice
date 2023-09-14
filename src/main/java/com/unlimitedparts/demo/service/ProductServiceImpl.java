package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.domain.repository.ProductRepository;
import com.unlimitedparts.demo.domain.repository.SaleRepository;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void addProduct(CreateProductRequest productRequest) {
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
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productRepository.getProductById(id));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
}
