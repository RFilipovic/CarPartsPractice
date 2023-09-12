package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.domain.Sale;
import com.unlimitedparts.demo.service.request.CreateSaleRequest;

public interface SaleService {

    Sale addSale(CreateSaleRequest saleRequest);
}
