package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.DTO.CarDTO;
import com.unlimitedparts.demo.model.Car;
import com.unlimitedparts.demo.request.CreateCarRequest;

public interface CarService {

    Car addCar(CreateCarRequest carRequest);
    CarDTO getCarById(Long id);
}
