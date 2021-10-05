package com.project.Dealership.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDescription {

    private Integer status;
    private OffsetDateTime time;
    private String title;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter
    public static class Field {

        private String name;
        private String message;

    }

}
