package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.service.DTO.CarDTO;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.service.request.CreateCarRequest;

import java.util.Optional;

public interface CarService {

    void addCar(CreateCarRequest carRequest);
    CarDTO getCarDTOById(Long id);
    Optional<Car> getCarById(Long id);
    void deleteCarById(Long id);
}
