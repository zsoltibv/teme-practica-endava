package com.endava.springdataexample.repository;

import com.endava.springdataexample.model.Car;
import com.endava.springdataexample.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Integer> {

    @Query("select g from Garage g where g.capacity > :capacity")
    List<Garage> findAllByCapacityGreaterThan(int capacity);
}
