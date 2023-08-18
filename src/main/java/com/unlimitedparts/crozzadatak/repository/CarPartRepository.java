package com.unlimitedparts.crozzadatak.repository;

import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.model.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart,Long> {

    CarPart getCarPartBySerialNumber(String serialNumber);
    CarPart getCarPartByDateOfCreation(LocalDate dateOfCreation);
    CarPart getCarPartById(Long id);
    List<CarPart> findDistinctByCarsNameAndCarsBrandName(String carName, String brandName);
    void deleteById(Long id);
    List<CarPart> findAllByIdIn(List<Long> carPartIds);

}
