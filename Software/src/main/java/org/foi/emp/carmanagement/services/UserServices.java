package org.foi.emp.carmanagement.services;


import org.foi.emp.carmanagement.models.UserModel;
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

    public List<UserModel> getAllUsers() {
        return this.userRepo.findAll();
    }

    public UserModel addNewUser(UserModel userModel) {
        return this.userRepo.save(userModel);
    }

    public void deleteUser(Long id) {
        this.userRepo.deleteUsersById(id);
    }

    public HttpStatus updateUser(UserModel parsedUserModel) {

        final Optional<UserModel> optionalUser = this.userRepo.findById(parsedUserModel.getId());

        if (optionalUser.isPresent()) {
            final UserModel users = optionalUser.get();
            final UserModel updatedUserModel = this.updateExistingUser(users, parsedUserModel);
            this.userRepo.save(updatedUserModel);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    private UserModel updateExistingUser(UserModel userModel, UserModel parsedUserModel) {

        userModel.setName(parsedUserModel.getName());
        userModel.setSurname(parsedUserModel.getSurname());
        userModel.setDateBirth(parsedUserModel.getDateBirth());
        userModel.setEmail(parsedUserModel.getEmail());
        return userModel;
    }

    public boolean checkIfUserExists(Long id) {
        return this.userRepo.existsUserById(id);
    }

    public boolean checkIfUserExists(String username){
        return this.userRepo.existsUserModelsByUsername(username);
    }

    public UserModel getUserModelByUsername(String username){
        return this.userRepo.getUserModelByUsername(username);
    }
}
