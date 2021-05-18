package org.foi.emp.carmanagement.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateBirth;

    public UserModel(final String username, final String password, final Collection<? extends GrantedAuthority> grants) {
        this.username = username;
        this.password = password;
    }

}
