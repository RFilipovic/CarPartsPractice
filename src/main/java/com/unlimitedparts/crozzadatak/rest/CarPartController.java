package com.unlimitedparts.crozzadatak.rest;
import com.unlimitedparts.crozzadatak.model.CarPart;
import com.unlimitedparts.crozzadatak.request.CreateCarPartRequest;
import com.unlimitedparts.crozzadatak.service.CarPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car-part")
public class CarPartController {

    private CarPartService carPartService;

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

    @GetMapping("/serial-number/{serialNumber}")
    public ResponseEntity<CarPart> getCarPartBySerialNumber(@PathVariable String serialNumber){
        CarPart carPart = carPartService.getBySerialNumber(serialNumber);

        if (carPart != null){
            return ResponseEntity.ok(carPart);
        }else{
            return ResponseEntity.notFound().build( );
        }
    }

    @GetMapping("/date-of-creation/{dateOfCreation}")
    public ResponseEntity<CarPart> getCarPartByDateOfCreation(@PathVariable LocalDate dateOfCreation){
        CarPart carPart = carPartService.getCarPartByDateOfCreation(dateOfCreation);

        if (carPart != null){
            return ResponseEntity.ok(carPart);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name-and-brand/{name}/{brandName}")
    public ResponseEntity<List<CarPart>> findDistinctByCarsNameAndCarsBrandName(@PathVariable String name, @PathVariable String brandName){
        List<CarPart> carParts = carPartService.findDistinctByCarsNameAndCarsBrandName(name,brandName);
        if (carParts != null){
            return ResponseEntity.ok(carParts);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteCarPart(@PathVariable Long id){
        CarPart carPart = carPartService.getCarPartById(id);
        if (carPart != null){
            carPartService.deleteById(id);
            return ResponseEntity.ok("Car part was deleted successfully.");
        }
        return ResponseEntity.badRequest().body("The car part was not removed.");
    }

}
