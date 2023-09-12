package com.unlimitedparts.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @OneToMany(mappedBy = "brand")
    private List<Car> cars;

    @Column(name = "brand_name")
    private String name;

    public Brand() {

    }

    public Long getId() {
        return brandId;
    }

    public void setId(Long id) {
        this.brandId = id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getBrandName() {
        return name;
    }

    public void setBrandName(String brandName) {
        this.name = brandName;
    }
}
