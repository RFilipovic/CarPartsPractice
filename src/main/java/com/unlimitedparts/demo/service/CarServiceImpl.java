package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.service.DTO.CarDTO;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.domain.repository.BrandRepository;
import com.unlimitedparts.demo.domain.repository.CarPartRepository;
import com.unlimitedparts.demo.domain.repository.CarRepository;
import com.unlimitedparts.demo.service.request.CreateCarRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void addCar(CreateCarRequest carRequest) {

        Long brandId = carRequest.getBrandId();
        List<Long> carPartIds = carRequest.getCarPartIds();
        String name = carRequest.getName();

        List<CarPart> carParts = carPartRepository.findAllByIdIn(carPartIds);
        Brand brand = brandRepository.getBrandById(brandId);

        Car car = new Car();

        car.setName(name);
        car.setBrand(brand);
        car.setCarParts(carParts);

        carRepository.save(car);
    }

    @Override
    public CarDTO getCarDTOById(Long id) {
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

    @Override
    @Transactional
    public void deleteCarById(Long id) {
        carRepository.deleteCarById(id);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return Optional.ofNullable(carRepository.getCarById(id));
    }
}
