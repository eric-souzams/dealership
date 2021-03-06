package com.project.Dealership.model.entity;

import com.project.Dealership.model.enums.Situation;
import com.project.Dealership.model.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Enumerated(EnumType.ORDINAL)
    private Situation situation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private CarModel model;

    private Double price;

    @ElementCollection()
    private List<FileUpload> files = new ArrayList<>();

    public void addFiles(List<FileUpload> files) {
        this.getFiles().addAll(files);
    }

}
