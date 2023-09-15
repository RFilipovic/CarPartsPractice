package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.service.DTO.CarPartDTO;
import com.unlimitedparts.demo.service.DTO.NameDTO;
import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.service.request.CreateCarPartRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarPartService {

    void addCarPart(CreateCarPartRequest carPart);
    CarPartDTO getCarPartDTOById(Long id);
    Optional<CarPart> getCarPartById(Long id);
    CarPartDTO getCarPartBySerialNumber(String serialNumber);

    List<CarPartDTO> getCarPartByDateOfCreation(LocalDate dateOfCreation);

    List<CarPartDTO> getCarPartByBrandAndCarName(String brandNameAndCarName);
    void deleteCarPartById(Long id);
    List<NameDTO> getNameDtos();
}
