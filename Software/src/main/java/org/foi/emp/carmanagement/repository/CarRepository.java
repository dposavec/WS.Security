package org.foi.emp.carmanagement.repository;

import org.foi.emp.carmanagement.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    void deleteCarById(Long id);

    boolean existsCarById(Long id);

}
