package com.unlimitedparts.demo.service;
import com.unlimitedparts.demo.service.DTO.CarPartDTO;
import com.unlimitedparts.demo.service.DTO.NameDTO;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.domain.repository.CarPartRepository;
import com.unlimitedparts.demo.domain.repository.CarRepository;
import com.unlimitedparts.demo.service.request.CreateCarPartRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void addCarPart(CreateCarPartRequest carPartRequest) {
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

        carPartRepository.save(carPart);
    }


    @Override
    public CarPartDTO getCarPartDTOById(Long id) {
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
    public Optional<CarPart> getCarPartById(Long id) {
        return Optional.ofNullable(carPartRepository.getCarPartById(id));
    }

    @Override
    public CarPartDTO getCarPartBySerialNumber(String serialNumber) {
        CarPart carPart = carPartRepository.getCarPartBySerialNumber(serialNumber);
        if (carPart != null){
            return getCarPartDTOById(carPart.getId());
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
                carPartDTOS.add(getCarPartDTOById(carPart.getId()));
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
    @Transactional
    public void deleteById(Long id) {
        carPartRepository.deleteById(id);
    }

    @Override
    public List<NameDTO> getNameDtos() {
        List<NameDTO> dtos = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car car : cars)
            {
                NameDTO dto = new NameDTO();
                dto.setBrandAndName(car.getBrand().getBrandName() + " " + car.getName());
                dto.setSize((long) car.getCarParts().size());
                dtos.add(dto);
            }

        return dtos;
    }
}
