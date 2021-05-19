package org.foi.emp.carmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.foi.emp.carmanagement.models.UserModel;
import org.foi.emp.carmanagement.services.EmailSenderService;
import org.foi.emp.carmanagement.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return new ResponseEntity<>(this.userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> addNewUser(@RequestBody UserModel parsedUserModel) {
        UserModel userModel = this.userServices.addNewUser(parsedUserModel);
        this.emailSenderService.sendActivationEmail(userModel.getEmail(),userModel,"Account activation");
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserModel parsedUserModel) {
        HttpStatus status = this.userServices.updateUser(parsedUserModel);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        if (this.userServices.checkIfUserExists(id)) {
            this.userServices.deleteUser(id);
            return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User doesn t exist in database and can t been deleted!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{activationCode}")
    public ResponseEntity<String> activateUser(@PathVariable("userId") Long id, @PathVariable("activationCode") String activationCode) {
        Optional<UserModel> userModel = this.userServices.getUserById(id);
        if (userModel.isPresent() && StringUtils.equals(activationCode, userModel.get().getActivationCode())) {
            UserModel model=userModel.get();
            model.setActivated(true);
            model.setActivationDate(new Date());
            this.userServices.updateUser(model);
            return new ResponseEntity<>("Postoji korisnik i aktiviran mu je račun", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Korisnik ne postoji", HttpStatus.NOT_FOUND);
        }
    }

}
