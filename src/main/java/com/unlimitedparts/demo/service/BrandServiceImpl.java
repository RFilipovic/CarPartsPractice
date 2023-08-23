package com.unlimitedparts.demo.service;

import com.unlimitedparts.demo.service.DTO.BrandDTO;
import com.unlimitedparts.demo.domain.Brand;
import com.unlimitedparts.demo.domain.Car;
import com.unlimitedparts.demo.domain.repository.BrandRepository;
import com.unlimitedparts.demo.domain.repository.CarRepository;
import com.unlimitedparts.demo.service.request.CreateBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

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

    @Override
    public BrandDTO getBrandById(Long id) {

        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null){
            BrandDTO dto = new BrandDTO();
            dto.setId(brand.getId());
            dto.setName(brand.getBrandName());
            dto.setCarModelNames(brand.getCars().stream()
                    .map(Car::getName)
                    .collect(Collectors.toList()));
            return dto;
        }
        return null;
    }
}
