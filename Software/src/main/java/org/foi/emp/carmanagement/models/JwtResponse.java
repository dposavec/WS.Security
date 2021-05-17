package org.foi.emp.carmanagement.models;

import lombok.Getter;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    @Getter
    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
