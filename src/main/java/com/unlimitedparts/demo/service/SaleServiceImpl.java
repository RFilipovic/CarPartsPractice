package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Product;
import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.domain.repository.ProductRepository;
import com.unlimitedparts.demo.domain.repository.SaleRepository;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addSale(CreateSaleRequest saleRequest) {

        if (saleRequest.getFrom() == null || saleRequest.getTo() == null ||
        saleRequest.getPercentage() == null || saleRequest.getProductIds() == null)
            throw new IllegalArgumentException("Invalid sale request.");

        if (!saleRequest.getFrom().isBefore(saleRequest.getTo()))
            throw new IllegalArgumentException("Invalid sale request.");

        Sale sale = new Sale();
        sale.setFrom(saleRequest.getFrom());
        sale.setTo(saleRequest.getTo());
        sale.setPercentage(saleRequest.getPercentage());

        List<Long> productIds = saleRequest.getProductIds();
        if (!productIds.isEmpty()){
            List<Product> products = productRepository.findAllByIdIn(productIds);
            sale.setProducts(products);
        }
        saleRepository.save(sale);
    }
}
