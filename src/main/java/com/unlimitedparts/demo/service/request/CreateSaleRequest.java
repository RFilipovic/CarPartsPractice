package com.unlimitedparts.demo.service.request;

import java.time.LocalDate;
import java.util.List;

public class CreateSaleRequest {

    private LocalDate from;
    private LocalDate to;
    private Double percentage;
    private List<Long> productIds;

    public CreateSaleRequest(){}

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
