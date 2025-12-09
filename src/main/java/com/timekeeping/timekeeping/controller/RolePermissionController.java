package com.timekeeping.timekeeping.controller;

import com.timekeeping.timekeeping.dto.RolePermissionRequest;
import com.timekeeping.timekeeping.dto.RolePermissionResponse;
import com.timekeeping.timekeeping.service.RolePermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/role-permissions")
@CrossOrigin(origins = "*")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public ResponseEntity<List<RolePermissionResponse>> getAllRolePermissions() {
        List<RolePermissionResponse> rolePermissions = rolePermissionService.getAllRolePermissions();
        return ResponseEntity.ok(rolePermissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolePermissionResponse> getRolePermissionById(@PathVariable UUID id) {
        RolePermissionResponse rolePermission = rolePermissionService.getRolePermissionById(id);
        return ResponseEntity.ok(rolePermission);
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RolePermissionResponse>> getPermissionsByRole(@PathVariable UUID roleId) {
        List<RolePermissionResponse> rolePermissions = rolePermissionService.getPermissionsByRole(roleId);
        return ResponseEntity.ok(rolePermissions);
    }

    @PostMapping
    public ResponseEntity<RolePermissionResponse> createRolePermission(@Valid @RequestBody RolePermissionRequest request) {
        RolePermissionResponse rolePermission = rolePermissionService.createRolePermission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolePermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolePermission(@PathVariable UUID id) {
        rolePermissionService.deleteRolePermission(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/role/{roleId}/permission/{permissionId}")
    public ResponseEntity<Void> deleteRolePermissionByRoleAndPermission(@PathVariable UUID roleId, 
                                                                          @PathVariable UUID permissionId) {
        rolePermissionService.deleteRolePermissionByRoleAndPermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
