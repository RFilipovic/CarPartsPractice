package com.unlimitedparts.crozzadatak.repository;

import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByIdIn(List<Long> carIds);
    Car getCarById(Long id);
}
