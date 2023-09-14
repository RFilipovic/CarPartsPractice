package com.unlimitedparts.demo.controller;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.service.DTO.CarDTO;
import com.unlimitedparts.demo.service.request.CreateCarRequest;
import com.unlimitedparts.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/warehouse/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody CreateCarRequest carRequest){
        if (carRequest != null){
            carService.addCar(carRequest);
            return ResponseEntity.ok("Successfully added car.");
        }
        return ResponseEntity.badRequest().body("Could not add car.");
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId){
        CarDTO carDTO = carService.getCarDTOById(carId);
        if (carDTO != null){
            return ResponseEntity.ok(carDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCarById(@PathVariable Long carId){
        Optional<Car> carOptional = carService.getCarById(carId);
        if (carOptional.isPresent()){
            Car car = carOptional.get();
            for (CarPart carPart : car.getCarParts()){
                carPart.getCars().clear();
            }
            carService.deleteCarById(carId);
            return ResponseEntity.ok("Car was successfully deleted.");
        }
        return ResponseEntity.badRequest().build();
    }

}
