package com.unlimitedparts.crozzadatak.service;
import com.unlimitedparts.crozzadatak.DTO.CarDTO;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.request.CreateCarRequest;

public interface CarService {

    Car addCar(CreateCarRequest carRequest);
    CarDTO getCarById(Long id);
}
