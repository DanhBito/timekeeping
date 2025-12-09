package com.timekeeping.timekeeping.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionRequest {
    
    @NotNull(message = "Role ID is required")
    private UUID roleId;
    
    @NotNull(message = "Permission ID is required")
    private UUID permissionId;
}
