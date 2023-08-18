package com.unlimitedparts.crozzadatak.service;

import com.unlimitedparts.crozzadatak.model.Brand;
import com.unlimitedparts.crozzadatak.model.Car;
import com.unlimitedparts.crozzadatak.repository.BrandRepository;
import com.unlimitedparts.crozzadatak.repository.CarRepository;
import com.unlimitedparts.crozzadatak.request.CreateBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    private BrandRepository brandRepository;
    private CarRepository carRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, CarRepository carRepository){
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Brand addBrand(CreateBrandRequest brandRequest) {
        if (brandRequest == null || brandRequest.getBrandName() == null || brandRequest.getCarIds() == null) {
            throw new IllegalArgumentException("Invalid brand request");
        }

        Brand brand = new Brand();
        brand.setBrandName(brandRequest.getBrandName());

        List<Long> carIds = brandRequest.getCarIds();
        if (!carIds.isEmpty()) {
            List<Car> cars = carRepository.findAllByIdIn(carIds);
            brand.setCars(cars);
        }

        return brandRepository.save(brand);
    }
}
