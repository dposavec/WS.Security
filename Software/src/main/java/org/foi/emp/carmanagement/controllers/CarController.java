package org.foi.emp.carmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.foi.emp.carmanagement.models.Car;
import org.foi.emp.carmanagement.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cars")
public class CarController {

    @Autowired
    private CarServices carServices;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(this.carServices.getAllCars(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car parsedCar) {
        Car car = this.carServices.addNewCar(parsedCar);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateCar(@RequestBody Car parsedCar) {
        HttpStatus status = this.carServices.updateCar(parsedCar);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") Long id) {
        if (this.carServices.checkIfCarExists(id)) {
            this.carServices.deleteCar(id);
            return new ResponseEntity<>("Car has been deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car doesn t exist in database and can t deleted it!", HttpStatus.NOT_FOUND);
        }
    }
}