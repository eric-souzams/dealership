package com.project.Dealership.model.entity;

import com.project.Dealership.model.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate birthdate;

    private String cpf;

    private String address;

    @Enumerated(EnumType.ORDINAL)
    private Profile profile;

}
