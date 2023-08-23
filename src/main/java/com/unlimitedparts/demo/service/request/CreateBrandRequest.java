package com.unlimitedparts.demo.service.request;
import java.util.List;


public class CreateBrandRequest {

    private List<Long> carIds;
    private String brandName;

    public CreateBrandRequest(){

    }

    public List<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Long> carsIds) {
        this.carIds = carsIds;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
