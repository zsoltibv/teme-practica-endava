package com.endava.springdataexample.controller;

import com.endava.springdataexample.model.Car;
import com.endava.springdataexample.model.Garage;
import com.endava.springdataexample.repository.CarRepository;
import com.endava.springdataexample.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/garage")
public class GarageController {
    @Autowired
    private GarageRepository garageRepository;

    @GetMapping
    public ResponseEntity<List<Garage>> getAll() {
        List<Garage> allGarages = garageRepository.findAll();
        if (allGarages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(allGarages);
        }
    }

    @GetMapping("/greaterThan")
    public ResponseEntity<List<Garage>> getGaragesByCapacityGreaterThan(@RequestParam("capacity") int capacity) {
        List<Garage> garages = garageRepository.findAllByCapacityGreaterThan(capacity);
        if (garages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(garages, HttpStatus.OK);
    }
}
