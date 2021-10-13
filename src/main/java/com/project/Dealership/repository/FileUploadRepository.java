package com.project.Dealership.repository;

import com.project.Dealership.model.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileUploadRepository extends JpaRepository<FileUpload, UUID> {
}
