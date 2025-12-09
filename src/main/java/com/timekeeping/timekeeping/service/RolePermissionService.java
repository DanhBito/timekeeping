package com.timekeeping.timekeeping.service;

import com.timekeeping.timekeeping.dto.RolePermissionRequest;
import com.timekeeping.timekeeping.dto.RolePermissionResponse;
import com.timekeeping.timekeeping.entity.Permission;
import com.timekeeping.timekeeping.entity.Role;
import com.timekeeping.timekeeping.entity.RolePermission;
import com.timekeeping.timekeeping.repository.PermissionRepository;
import com.timekeeping.timekeeping.repository.RolePermissionRepository;
import com.timekeeping.timekeeping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public List<RolePermissionResponse> getAllRolePermissions() {
        return rolePermissionRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RolePermissionResponse getRolePermissionById(UUID id) {
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission not found with id: " + id));
        return convertToResponse(rolePermission);
    }

    public List<RolePermissionResponse> getPermissionsByRole(UUID roleId) {
        return rolePermissionRepository.findByRoleId(roleId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RolePermissionResponse createRolePermission(RolePermissionRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + request.getRoleId()));
        
        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + request.getPermissionId()));

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(role);
        rolePermission.setPermission(permission);

        RolePermission savedRolePermission = rolePermissionRepository.save(rolePermission);
        return convertToResponse(savedRolePermission);
    }

    public void deleteRolePermission(UUID id) {
        if (!rolePermissionRepository.existsById(id)) {
            throw new RuntimeException("RolePermission not found with id: " + id);
        }
        rolePermissionRepository.deleteById(id);
    }

    public void deleteRolePermissionByRoleAndPermission(UUID roleId, UUID permissionId) {
        rolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    private RolePermissionResponse convertToResponse(RolePermission rolePermission) {
        RolePermissionResponse response = new RolePermissionResponse();
        response.setId(rolePermission.getId());
        response.setRoleId(rolePermission.getRole().getId());
        response.setRoleName(rolePermission.getRole().getName());
        response.setPermissionId(rolePermission.getPermission().getId());
        response.setPermissionCode(rolePermission.getPermission().getCode());
        return response;
    }
}
