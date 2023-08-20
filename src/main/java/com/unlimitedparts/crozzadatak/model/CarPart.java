package com.unlimitedparts.crozzadatak.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_parts")
public class CarPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_part_id")
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @ManyToMany(mappedBy = "carParts")
    private List<Car> cars;

    public CarPart(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        if (cars == null){
            cars = new ArrayList<>();
        }
        cars.add(car);
        car.getCarParts().add(this);
    }


}
