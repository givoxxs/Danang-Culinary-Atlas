package com.atlasculinary.dtos.profile;

import com.atlasculinary.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {
    
  @Size(max = 100, message = "Tên đầy đủ không được vượt quá 100 ký tự")
  private String fullName;
  
  private String avatarUrl;
  
  @Past(message = "Ngày sinh phải là ngày trong quá khứ")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dob;
  
  private Gender gender;
}