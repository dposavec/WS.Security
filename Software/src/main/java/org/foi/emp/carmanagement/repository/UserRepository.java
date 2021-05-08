package org.foi.emp.carmanagement.repository;

import org.foi.emp.carmanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteUsersById(Long id);

    boolean existsUserById(Long id);

}
