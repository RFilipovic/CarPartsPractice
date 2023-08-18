package com.unlimitedparts.crozzadatak.service;

import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.request.CreateCarRequest;

public interface CarService {

    Car addCar(CreateCarRequest carRequest);
    Brand getBrandById(Long carId);
    Car getCarById(Long id);
}
