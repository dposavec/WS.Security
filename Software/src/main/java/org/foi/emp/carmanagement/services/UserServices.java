package org.foi.emp.carmanagement.services;


import org.foi.emp.carmanagement.models.User;
import org.foi.emp.carmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User addNewUser(User user) {
        return this.userRepo.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepo.deleteUsersById(id);
    }

    public HttpStatus updateUser(User parsedUser) {

        final Optional<User> optionalUser = this.userRepo.findById(parsedUser.getId());

        if (optionalUser.isPresent()) {
            final User users = optionalUser.get();
            final User updatedUser = this.updateExistingUser(users, parsedUser);
            this.userRepo.save(updatedUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    private User updateExistingUser(User user, User parsedUser) {

        user.setName(parsedUser.getName());
        user.setSurname(parsedUser.getSurname());
        user.setDateBirth(parsedUser.getDateBirth());
        user.setEducation(parsedUser.getEducation());
        return user;
    }

    public boolean checkIfUserExists(Long id) {
        return this.userRepo.existsUserById(id);
    }
}
