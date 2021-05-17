package org.foi.emp.carmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.foi.emp.carmanagement.models.User;
import org.foi.emp.carmanagement.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userServices.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User parsedUser) {
        User user = this.userServices.addNewUser(parsedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User parsedUser) {
        HttpStatus status = this.userServices.updateUser(parsedUser);
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
}
