package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.FileUpload;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FileUploadResponse {

    private static final String DIRECTORY = "file:///C:/Users/Eric/Downloads/uploads/";
    private UUID id;
    private String path;
    private LocalDateTime created_at;

    public static FileUploadResponse toResponse(FileUpload fileUpload) {
        FileUploadResponse result = new FileUploadResponse();

        result.setId(fileUpload.getId());
        result.setPath(DIRECTORY + fileUpload.getPath());
        result.setCreated_at(fileUpload.getCreated_at());

        return result;
    }

}
