package org.foi.emp.carmanagement.repository;

import org.foi.emp.carmanagement.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    void deleteUsersById(Long id);

    boolean existsUserById(Long id);

    boolean existsUserModelsByUsername(String username);

    UserModel getUserModelByUsername(String username);

}
