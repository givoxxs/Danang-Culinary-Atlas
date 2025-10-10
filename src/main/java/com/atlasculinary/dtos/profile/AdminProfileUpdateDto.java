package com.atlasculinary.dtos.profile;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfileUpdateDto {
    
  @Size(max = 100, message = "Tên đầy đủ không được vượt quá 100 ký tự")
  private String fullName;
  
  private String avatarUrl;
  
  @Pattern(regexp = "^[0-9]{10,15}$", message = "Số điện thoại phải từ 10-15 chữ số")
  private String phone;
}