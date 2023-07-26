package com.endava.springdataexample.controller;

import com.endava.springdataexample.model.Car;
import com.endava.springdataexample.model.Garage;
import com.endava.springdataexample.repository.CarRepository;
import com.endava.springdataexample.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageRepository garageRepository;

    @GetMapping
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping(path = "/byBrand")
    public List<Car> findAllByBrand(@RequestParam("brand") String brand) {
        return carRepository.findAllByBrand(brand);
    }

    @GetMapping(path = "/thisYear")
    public List<Car> findAllConstructedThisYear() {
        LocalDate firstDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate secondDate = LocalDate.of(LocalDate.now().getYear() + 1, 1, 1);
        return carRepository.findAllConstructedBetweenDates(firstDate, secondDate);
    }

    @GetMapping(path = "/withGarages")
    public ResponseEntity<List<Car>> getCarsWithGarageAssigned() {
        List<Car> carsFound = carRepository.findAllByGarageIsNotNull();

        if (carsFound.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(carsFound, HttpStatus.OK);
        }
    }

    @GetMapping("/garages/{garageId}")
    public ResponseEntity<List<Car>> getCarsByGarageId(@PathVariable Integer garageId) {
        List<Car> cars = carRepository.findAllByGarageId(garageId);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("/{carId}/garage/{garageId}")
    public ResponseEntity<String> updateCarGarage(@PathVariable int carId, @PathVariable int garageId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<Garage> optionalGarage = garageRepository.findById(garageId);

        if (optionalCar.isPresent() && optionalGarage.isPresent()) {
            Car car = optionalCar.get();
            Garage garage = optionalGarage.get();
            car.setGarage(garage);
            carRepository.save(car);
            return new ResponseEntity<>("Car's garage updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car or garage not found", HttpStatus.NOT_FOUND);
        }
    }
}
