package com.example.commentservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {
    private String code;
    private String message;
}
