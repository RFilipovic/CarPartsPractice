package com.unlimitedparts.demo.service.DTO;

import java.time.LocalDate;

public class ProductDTO {

    private String serialNumber;
    private LocalDate dateOfCreation;
    private Double basePrice;
    private Long saleId;

    public ProductDTO(){}


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
