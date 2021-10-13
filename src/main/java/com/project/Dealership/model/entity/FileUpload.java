package com.project.Dealership.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class FileUpload {

    @Id
    private UUID id;

    private String path;

    private LocalDateTime created_at;

}
