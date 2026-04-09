package com.klu.studentcrud.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API error response")
public class ApiErrorResponse {

    @Schema(example = "Student not found with ID: 999")
    private String message;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
