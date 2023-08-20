package com.unlimitedparts.crozzadatak.rest;
import com.unlimitedparts.crozzadatak.DTO.CarDTO;
import com.unlimitedparts.crozzadatak.request.CreateCarRequest;
import com.unlimitedparts.crozzadatak.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
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
        CarDTO carDTO = carService.getCarById(carId);
        if (carDTO != null){
            return ResponseEntity.ok(carDTO);
        }
        return ResponseEntity.badRequest().build();
    }

}
