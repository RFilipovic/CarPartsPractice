package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.domain.repository.CarPartRepository;
import com.unlimitedparts.demo.domain.repository.ProductRepository;
import com.unlimitedparts.demo.domain.repository.SaleRepository;
import com.unlimitedparts.demo.service.DTO.ProductDTO;
import com.unlimitedparts.demo.service.request.CreateProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final CarPartRepository carPartRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SaleRepository saleRepository, CarPartRepository carPartRepository){
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.carPartRepository = carPartRepository;
    }

    @Override
    public void addProduct(CreateProductRequest productRequest) {
        if (productRequest.getSerialNumber() == null || productRequest.getBasePrice() == null
        || productRequest.getSaleId() == null)
            throw new IllegalArgumentException("Invalid product request.");

        CarPart carPart = carPartRepository.getCarPartBySerialNumber(productRequest.getSerialNumber());
        if (carPart == null)
            throw new IllegalArgumentException("Invalid serial number.");

        Product product = new Product();
        product.setSerialNumber(productRequest.getSerialNumber());
        product.setBasePrice(productRequest.getBasePrice());
        product.setCarPart(carPart);
        Long saleId = productRequest.getSaleId();
        if (saleId != null){
            Sale sale = saleRepository.getSaleById(saleId);
            product.setSale(sale);
        }else{
            product.setSale(null);
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

    @Override
    public boolean updateProduct(Product product, CreateProductRequest productRequest) {
        if (productRequest.getSerialNumber() == null || productRequest.getBasePrice() == null
                || productRequest.getSaleId() == null)
            throw new IllegalArgumentException("Invalid product request for update.");
        if (product.getSerialNumber().equals(productRequest.getSerialNumber())){
            product.setBasePrice(productRequest.getBasePrice());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getProductDTOById(Long id) {
        Product product = productRepository.getProductById(id);
        if (product != null){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDateOfCreation(product.getCarPart().getDateOfCreation());
            productDTO.setSaleId(product.getSale().getSaleId());
            productDTO.setSerialNumber(product.getSerialNumber());
            productDTO.setBasePrice(product.getBasePrice());
            productDTO.setFinalPrice(product.getBasePrice() * (1-(product.getSale().getPercentage()/100)));
            return productDTO;
        }
        return null;
    }
}
