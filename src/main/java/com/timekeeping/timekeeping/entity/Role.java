package com.timekeeping.timekeeping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name = "name", nullable = false, length = 50)
    private String name; // SYSTEM_ADMIN, OWNER, MANAGER, EMPLOYEE
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<RolePermission> rolePermissions = new HashSet<>();
}
