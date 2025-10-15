package com.atlasculinary.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
  private String status;
  private String message;
    
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;

  public static ApiResponse success(String message) {
    return ApiResponse.builder()
            .status("success")
            .message(message)
            .build();
  }

  public static ApiResponse success(String message, Object data) {
    return ApiResponse.builder()
            .status("success")
            .message(message)
            .data(data)
            .build();
  }

  public static ApiResponse error(String message) {
    return ApiResponse.builder()
            .status("error")
            .message(message)
            .build();
  }
}