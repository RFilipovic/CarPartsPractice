package com.unlimitedparts.demo.domain.repository;

import com.unlimitedparts.demo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByIdIn(List<Long> carIds);
    Car getCarById(Long id);
}
