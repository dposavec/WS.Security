package org.foi.emp.carmanagement.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int hp;
    private int kw;
    private int price;
    private int yearModel;

}

