package com.unlimitedparts.crozzadatak.service;

import com.unlimitedparts.crozzadatak.DTO.CarDTO;
import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.repository.BrandRepository;
import com.unlimitedparts.crozzadatak.repository.CarPartRepository;
import com.unlimitedparts.crozzadatak.repository.CarRepository;
import com.unlimitedparts.crozzadatak.request.CreateCarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final CarPartRepository carPartRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, BrandRepository brandRepository, CarPartRepository carPartRepository){
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.carPartRepository = carPartRepository;
    }

    @Override
    public Car addCar(CreateCarRequest carRequest) {

        Long brandId = carRequest.getBrandId();
        List<Long> carPartIds = carRequest.getCarPartIds();
        String name = carRequest.getName();

        List<CarPart> carParts = carPartRepository.findAllByIdIn(carPartIds);
        Brand brand = brandRepository.getBrandById(brandId);

        Car car = new Car();

        car.setName(name);
        car.setBrand(brand);

        car.setCarParts(carParts);
        return carRepository.save(car);
    }

    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.getCarById(id);
        if (car != null){
            CarDTO dto = new CarDTO();
            dto.setId(car.getId());
            dto.setName(car.getName());
            dto.setBrandName(car.getBrand().getBrandName());
            return dto;
        }
        return null;
    }
}
