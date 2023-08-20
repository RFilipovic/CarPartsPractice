package com.unlimitedparts.crozzadatak.service;
import com.unlimitedparts.crozzadatak.DTO.CarPartDTO;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;

import java.time.LocalDate;
import java.util.List;

public interface CarPartService {

    CarPart addCarPart(CreateCarPartRequest carPart);
    CarPartDTO getCarPartById(Long id);
    void deleteById(Long id);

    CarPartDTO getCarPartBySerialNumber(String serialNumber);

    List<CarPartDTO> getCarPartByDateOfCreation(LocalDate dateOfCreation);

    List<CarPartDTO> getCarPartByBrandAndCarName(String brandNameAndCarName);
}
