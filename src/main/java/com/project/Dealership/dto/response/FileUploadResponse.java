package com.project.Dealership.dto.response;

import com.project.Dealership.model.entity.FileUpload;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FileUploadResponse {

    private static final String DIRECTORY = "file:///C:/Users/Eric/Downloads/uploads/";

    private UUID id;

    private String path;

    public static FileUploadResponse toResponse(FileUpload fileUpload) {
        FileUploadResponse result = new FileUploadResponse();

        result.setId(fileUpload.getId());
        result.setPath(DIRECTORY + fileUpload.getPath());

        return result;
    }

}
