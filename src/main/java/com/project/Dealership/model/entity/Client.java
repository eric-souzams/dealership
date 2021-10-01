package com.project.Dealership.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate birthdate;

    private String cpf;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> boughtCars = new ArrayList<>();

    public void addCar(Car car) {
        this.getBoughtCars().add(car);
    }

}
