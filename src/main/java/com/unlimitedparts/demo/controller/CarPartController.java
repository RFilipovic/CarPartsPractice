package com.unlimitedparts.demo.controller;
import com.unlimitedparts.demo.service.DTO.CarPartDTO;
import com.unlimitedparts.demo.service.DTO.NameDTO;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.domain.CarPart;
import com.unlimitedparts.demo.service.request.CreateCarPartRequest;
import com.unlimitedparts.demo.service.CarPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/car-part")
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

    @GetMapping
    public ResponseEntity<?> getCarPartById(
            @RequestParam(required = false) Long carPartId,
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) LocalDate dateOfCreation,
            @RequestParam(required = false) String brandNameAndCarName,
            @RequestParam(required = false, defaultValue = "false") boolean numberOfParts
    ){
        if (carPartId != null) {
            CarPartDTO dto = carPartService.getCarPartDTOById(carPartId);
            if (dto != null) {
                return ResponseEntity.ok(dto);
            }
        } else if (serialNumber != null) {
            CarPartDTO dto = carPartService.getCarPartBySerialNumber(serialNumber);
            if (dto != null) {
                return ResponseEntity.ok(dto);
            }
        } else if (dateOfCreation != null) {
            List<CarPartDTO> dtos = carPartService.getCarPartByDateOfCreation(dateOfCreation);
            if (!dtos.isEmpty()) {
                return ResponseEntity.ok(dtos);
            }
        } else if (brandNameAndCarName != null) {
            List<CarPartDTO> dtos = carPartService.getCarPartByBrandAndCarName(brandNameAndCarName);
            if (!dtos.isEmpty()) {
                return ResponseEntity.ok(dtos);
            }
        } else if (numberOfParts) {
            List<NameDTO> dtos = carPartService.getNameDtos();
            if (!dtos.isEmpty()) {
                return ResponseEntity.ok(dtos);
            }
        }

        return ResponseEntity.badRequest().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarPartById(@PathVariable Long id){
        Optional<CarPart> carPartOptional = carPartService.getCarPartById(id);
        if (carPartOptional.isPresent()){
            CarPart carPart = carPartOptional.get();

            for (Car car : carPart.getCars()){
                car.getCarParts().remove(carPart);
            }
            carPartService.deleteCarPartById(id);
            return ResponseEntity.ok("CarPart deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}


