package org.foi.emp.carmanagement.services;


import org.foi.emp.carmanagement.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (this.userServices.checkIfUserExists(username)) {
            final UserModel userModel=this.userServices.getUserModelByUsername(username);
            return new User(username, this.passwordEncoder(userModel.getPassword()),
                    new ArrayList<>());

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

    private String passwordEncoder(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
