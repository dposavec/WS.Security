package org.foi.emp.carmanagement.services;

import org.foi.emp.carmanagement.models.Car;
import org.foi.emp.carmanagement.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServices {

    @Autowired
    private CarRepository repo;

    public List<Car> getAllCars() {
        return this.repo.findAll();
    }

    public Car addNewCar(Car car) {
        return this.repo.save(car);
    }

    public void deleteCar(Long id) {
        this.repo.deleteCarById(id);
    }

    public HttpStatus updateCar(Car parsedCar) {
        final Optional<Car> optionalCar = this.repo.findById(parsedCar.getId());
        if (optionalCar.isPresent()) {
            final Car car = optionalCar.get();
            car.setHp(parsedCar.getHp());
            car.setKw(parsedCar.getKw());
            car.setName(parsedCar.getName());
            car.setPrice(parsedCar.getPrice());
            car.setYearModel(parsedCar.getYearModel());
            this.repo.save(car);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }

    }
}
