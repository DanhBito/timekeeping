package com.timekeeping.timekeeping.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    
    @NotBlank(message = "Permission code is required")
    private String code;
    
    private String description;
}
