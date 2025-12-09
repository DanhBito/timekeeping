package com.timekeeping.timekeeping.service;

import com.timekeeping.timekeeping.dto.PermissionRequest;
import com.timekeeping.timekeeping.dto.PermissionResponse;
import com.timekeeping.timekeeping.entity.Permission;
import com.timekeeping.timekeeping.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public PermissionResponse getPermissionById(UUID id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return convertToResponse(permission);
    }

    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = new Permission();
        permission.setCode(request.getCode());
        permission.setDescription(request.getDescription());
        Permission savedPermission = permissionRepository.save(permission);
        return convertToResponse(savedPermission);
    }

    public PermissionResponse updatePermission(UUID id, PermissionRequest request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        permission.setCode(request.getCode());
        permission.setDescription(request.getDescription());
        Permission updatedPermission = permissionRepository.save(permission);
        return convertToResponse(updatedPermission);
    }

    public void deletePermission(UUID id) {
        if (!permissionRepository.existsById(id)) {
            throw new RuntimeException("Permission not found with id: " + id);
        }
        permissionRepository.deleteById(id);
    }

    private PermissionResponse convertToResponse(Permission permission) {
        PermissionResponse response = new PermissionResponse();
        response.setId(permission.getId());
        response.setCode(permission.getCode());
        response.setDescription(permission.getDescription());
        return response;
    }
}
