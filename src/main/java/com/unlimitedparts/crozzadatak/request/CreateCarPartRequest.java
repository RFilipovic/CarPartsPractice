package com.unlimitedparts.crozzadatak.request;

import java.time.LocalDate;
import java.util.List;

public class CreateCarPartRequest {

    private List<Long> carIds;
    private String serialNumber;
    private LocalDate dateOfCreation;

    public CreateCarPartRequest() {

    }

    public List<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Long> carIds) {
        this.carIds = carIds;
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
