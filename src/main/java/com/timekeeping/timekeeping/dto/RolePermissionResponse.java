package com.timekeeping.timekeeping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResponse {
    private UUID id;
    private UUID roleId;
    private String roleName;
    private UUID permissionId;
    private String permissionCode;
}
