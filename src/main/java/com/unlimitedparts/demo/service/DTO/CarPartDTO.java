package com.unlimitedparts.demo.service.DTO;

import java.time.LocalDate;

public class CarPartDTO {

    private Long id;
    private String serialNumber;
    private LocalDate dateOfCreation;

    public CarPartDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
