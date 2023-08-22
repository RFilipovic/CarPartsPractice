package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.DTO.CarPartDTO;
import com.unlimitedparts.demo.DTO.NameDTO;
import com.unlimitedparts.demo.model.CarPart;
import com.unlimitedparts.demo.request.CreateCarPartRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarPartService {

    CarPart addCarPart(CreateCarPartRequest carPart);
    CarPartDTO getCarPartDTOById(Long id);
    Optional<CarPart> getCarPartById(Long id);
    CarPartDTO getCarPartBySerialNumber(String serialNumber);

    List<CarPartDTO> getCarPartByDateOfCreation(LocalDate dateOfCreation);

    List<CarPartDTO> getCarPartByBrandAndCarName(String brandNameAndCarName);
    void deleteById(Long id);
    List<NameDTO> getNameDtos();
}
