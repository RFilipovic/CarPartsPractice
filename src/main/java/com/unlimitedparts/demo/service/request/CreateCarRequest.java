package com.unlimitedparts.demo.service.request;
import java.util.List;

public class CreateCarRequest {

    private List<Long> carPartIds;
    private Long brandId;
    private String name;

    public CreateCarRequest(){

    }

    public List<Long> getCarPartIds() {
        return carPartIds;
    }

    public void setCarPartIds(List<Long> carPartIds) {
        this.carPartIds = carPartIds;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
