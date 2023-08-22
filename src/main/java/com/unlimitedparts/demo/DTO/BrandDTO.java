package com.unlimitedparts.demo.DTO;

import java.util.List;

public class BrandDTO {

    private Long id;
    private String name;
    private List<String> carModelNames;

    public BrandDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCarModelNames() {
        return carModelNames;
    }

    public void setCarModelNames(List<String> carModelNames) {
        this.carModelNames = carModelNames;
    }
}
