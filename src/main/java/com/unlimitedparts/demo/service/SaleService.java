package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;

import java.util.Optional;

public interface SaleService {

    void addSale(CreateSaleRequest saleRequest);
    void deleteSaleById(Long id);
    Optional<Sale> getSaleById(Long id);
}
