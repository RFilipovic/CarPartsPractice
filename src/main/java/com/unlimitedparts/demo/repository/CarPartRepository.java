package com.unlimitedparts.demo.repository;
import com.unlimitedparts.demo.model.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart,Long> {

    CarPart getCarPartBySerialNumber(String serialNumber);
    List<CarPart> getAllByDateOfCreation(LocalDate dateOfCreation);
    CarPart getCarPartById(Long id);
    @Query("SELECT cp FROM CarPart cp " +
            "JOIN cp.cars c " +
            "JOIN c.brand b " +
            "WHERE CONCAT(b.name, c.name) = :brandCarName")
    List<CarPart> findByBrandCarName(@Param("brandCarName") String brandCarName);
    void deleteCarPartById(Long id);
    List<CarPart> findAllByIdIn(List<Long> carPartIds);

}
