package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;

import java.util.List;

public interface RoleServiceInterface {
    void addRole(RoleName roleName);
    void deleteRole(RoleName roleName);
    List<Role> getAllRoles();
    void AddALLRoles();
}
