package com.unlimitedparts.crozzadatak.service;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.repository.CarPartRepository;
import com.unlimitedparts.crozzadatak.repository.CarRepository;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarPartServiceImpl implements CarPartService{

    private CarPartRepository carPartRepository;

    private CarRepository carRepository;

    @Autowired
    public CarPartServiceImpl(CarPartRepository carPartRepository, CarRepository carRepository){
        this.carPartRepository = carPartRepository;
        this.carRepository = carRepository;
    }


    @Override
    public CarPart getBySerialNumber(String serialNumber) {
        return carPartRepository.getCarPartBySerialNumber(serialNumber);
    }

    @Override
    public CarPart addCarPart(CreateCarPartRequest carPartRequest) {
        List<Long> carIds = carPartRequest.getCarIds();
        String serialNumber = carPartRequest.getSerialNumber();
        LocalDate date = carPartRequest.getDateOfCreation();

        List<Car> cars = carRepository.findAllByIdIn(carIds);

        CarPart carPart = new CarPart();

        carPart.setSerialNumber(serialNumber);
        carPart.setDateOfCreation(date);
        carPart.setCars(cars);

        return carPartRepository.save(carPart);
    }

    @Override
    public CarPart getCarPartByDateOfCreation(LocalDate dateOfCreation) {
        return carPartRepository.getCarPartByDateOfCreation(dateOfCreation);
    }

    @Override
    public CarPart getCarPartById(Long id) {
        return carPartRepository.getCarPartById(id);
    }

    @Override
    public void deleteById(Long id) {
        carPartRepository.deleteById(id);
    }

    @Override
    public List<CarPart> findDistinctByCarsNameAndCarsBrandName(String carName, String brandName) {
        return carPartRepository.findDistinctByCarsNameAndCarsBrandName(carName, brandName);
    }
}
