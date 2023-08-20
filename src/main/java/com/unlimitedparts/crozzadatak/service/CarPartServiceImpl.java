package com.unlimitedparts.crozzadatak.service;
import com.unlimitedparts.crozzadatak.DTO.CarPartDTO;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.repository.CarPartRepository;
import com.unlimitedparts.crozzadatak.repository.CarRepository;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarPartServiceImpl implements CarPartService{

    private final CarPartRepository carPartRepository;

    private final CarRepository carRepository;

    @Autowired
    public CarPartServiceImpl(CarPartRepository carPartRepository, CarRepository carRepository){
        this.carPartRepository = carPartRepository;
        this.carRepository = carRepository;
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

        for (Car car : cars){
            carPart.addCar(car);
        }

        return carPartRepository.save(carPart);
    }


    @Override
    public CarPartDTO getCarPartById(Long id) {
        CarPart carPart = carPartRepository.getCarPartById(id);
        if (carPart != null){
            CarPartDTO dto = new CarPartDTO();
            dto.setId(carPart.getId());
            dto.setSerialNumber(carPart.getSerialNumber());
            dto.setDateOfCreation(carPart.getDateOfCreation());
            return dto;
        }
        return null;
    }

    @Override
    public CarPartDTO getCarPartBySerialNumber(String serialNumber) {
        CarPart carPart = carPartRepository.getCarPartBySerialNumber(serialNumber);
        if (carPart != null){
            return getCarPartById(carPart.getId());
        }
        return null;
    }

    @Override
    public List<CarPartDTO> getCarPartByDateOfCreation(LocalDate dateOfCreation) {
        List<CarPart> carParts = carPartRepository.getAllByDateOfCreation(dateOfCreation);
        return getCarPartDTOS(carParts);
    }

    private List<CarPartDTO> getCarPartDTOS(List<CarPart> carParts) {
        if (carParts != null){
            List<CarPartDTO> carPartDTOS = new ArrayList<>();
            for (CarPart carPart : carParts){
                carPartDTOS.add(getCarPartById(carPart.getId()));
            }
            return carPartDTOS;
        }
        return null;
    }

    @Override
    public List<CarPartDTO> getCarPartByBrandAndCarName(String brandNameAndCarName) {
        List<CarPart> carParts = carPartRepository.findByBrandCarName(brandNameAndCarName);
        return getCarPartDTOS(carParts);
    }

    @Override
    public void deleteById(Long id) {
        carPartRepository.deleteById(id);
    }


}
