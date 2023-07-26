package com.endava.springdataexample.repository;

import com.endava.springdataexample.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("select c from Car c where c.brand = :brand")
    List<Car> findAllByBrand(@Param("brand") String brand);

    @Query("select c from Car c where c.constructionDate >= :firstDate and c.constructionDate < :secondDate")
    List<Car> findAllConstructedBetweenDates(@Param("firstDate") LocalDate firstDate, @Param("secondDate") LocalDate secondDate);

    List<Car> findAllByEngine(String engine);

    List<Car> findAllByGarageIsNotNull();

    List<Car> findAllByGarageId(Integer garageId);
}
