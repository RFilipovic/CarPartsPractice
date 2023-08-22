package com.unlimitedparts.demo.rest;
import com.unlimitedparts.demo.DTO.CarPartDTO;
import com.unlimitedparts.demo.DTO.NameDTO;
import com.unlimitedparts.demo.model.Car;
import com.unlimitedparts.demo.model.CarPart;
import com.unlimitedparts.demo.request.CreateCarPartRequest;
import com.unlimitedparts.demo.service.CarPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        CarPartDTO dto = carPartService.getCarPartDTOById(carPartId);
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

    @GetMapping("/brand-car/{brandNameAndCarName}")
    public ResponseEntity<List<CarPartDTO>> getCarPartByBrandAndCarName(@PathVariable String brandNameAndCarName){
        List<CarPartDTO> dtos = carPartService.getCarPartByBrandAndCarName(brandNameAndCarName);
        if (dtos != null){
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/number-of-parts")
    public ResponseEntity<List<NameDTO>> getNumberOfCarPartsByBrandAndName(){
        List<NameDTO> dtos = carPartService.getNameDtos();
        if (dtos != null){
            return ResponseEntity.ok(dtos);
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
            carPartService.deleteById(id);
            return ResponseEntity.ok("CarPart deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}


