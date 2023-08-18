package com.unlimitedparts.crozzadatak.service;

import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.repository.BrandRepository;
import com.unlimitedparts.crozzadatak.repository.CarPartRepository;
import com.unlimitedparts.crozzadatak.repository.CarRepository;
import com.unlimitedparts.crozzadatak.request.CreateCarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;
    private BrandRepository brandRepository;
    private CarPartRepository carPartRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, BrandRepository brandRepository, CarPartRepository carPartRepository){
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.carPartRepository = carPartRepository;
    }

    @Override
    public Brand getBrandById(Long carId) {
        return brandRepository.getBrandById(carId);
    }

    @Override
    public Car addCar(CreateCarRequest carRequest) {

        Long brandId = carRequest.getBrandId();
        List<Long> carPartIds = carRequest.getCarPartIds();
        String name = carRequest.getName();
        List<CarPart> carParts = carPartRepository.findAllByIdIn(carPartIds);

        Car car = new Car();

        car.setName(name);
        car.setBrand(getBrandById(brandId));
        car.setCarParts(carParts);
        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.getById(id);
    }
}
