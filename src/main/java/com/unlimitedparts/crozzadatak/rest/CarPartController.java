package com.unlimitedparts.crozzadatak.rest;
import com.unlimitedparts.crozzadatak.DTO.CarPartDTO;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;
import com.unlimitedparts.crozzadatak.service.CarPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car-part")
public class CarPartController {

    private final CarPartService carPartService;

    public CarPartController(CarPartService carPartService){
        this.carPartService = carPartService;
    }

    @PostMapping
    public ResponseEntity<String> addCarPart(@RequestBody CreateCarPartRequest carPart){
        if (carPart != null) {
            carPartService.addCarPart(carPart);
            return ResponseEntity.ok("Car part successfully added.");
        }
        return ResponseEntity.badRequest().body("Could not add car part");
    }

    @GetMapping("/{carPartId}")
    public ResponseEntity<CarPartDTO> getCarPartById(@PathVariable Long carPartId){
        CarPartDTO dto = carPartService.getCarPartById(carPartId);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/serial-number/{serialNumber}")
    public ResponseEntity<CarPartDTO> getCarPartBySerialNumber(@PathVariable String serialNumber){
        CarPartDTO dto = carPartService.getCarPartBySerialNumber(serialNumber);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/date-of-creation/{dateOfCreation}")
    public ResponseEntity<List<CarPartDTO>> getCarPartByDateOfCreation(@PathVariable LocalDate dateOfCreation){
        List<CarPartDTO> dtos = carPartService.getCarPartByDateOfCreation(dateOfCreation);
        if (dtos != null){
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/brand-carname/{brandNameAndCarName}")
    public ResponseEntity<List<CarPartDTO>> getCarPartByBrandAndCarName(@PathVariable String brandNameAndCarName){
        List<CarPartDTO> dtos = carPartService.getCarPartByBrandAndCarName(brandNameAndCarName);
        if (dtos != null){
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.badRequest().build();
    }
}


