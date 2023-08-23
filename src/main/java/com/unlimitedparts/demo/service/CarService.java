package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.service.DTO.CarDTO;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.service.request.CreateCarRequest;

public interface CarService {

    Car addCar(CreateCarRequest carRequest);
    CarDTO getCarById(Long id);
}
