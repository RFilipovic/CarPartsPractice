package com.unlimitedparts.crozzadatak.service;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;

import java.time.LocalDate;
import java.util.List;

public interface CarPartService {

    CarPart getBySerialNumber(String serialNumber);
    CarPart addCarPart(CreateCarPartRequest carPart);
    CarPart getCarPartByDateOfCreation(LocalDate dateOfCreation);
    CarPart getCarPartById(Long id);
    void deleteById(Long id);
    List<CarPart> findDistinctByCarsNameAndCarsBrandName(String carName, String brandName);
}
