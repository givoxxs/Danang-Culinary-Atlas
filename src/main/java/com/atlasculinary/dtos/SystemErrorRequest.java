package com.atlasculinary.dtos;

import lombok.Data;

@Data
public class SystemErrorRequest {
    private String errorTitle;
    private String errorMessage;
}
