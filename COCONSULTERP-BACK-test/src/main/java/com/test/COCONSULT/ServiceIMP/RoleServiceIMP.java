package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.RoleServiceInterface;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServiceIMP implements RoleServiceInterface {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addRole(RoleName roleName) {
        Role role=new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(RoleName roleName) {
        Role roleToDelete = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.delete(roleToDelete);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public void AddALLRoles(){
        for (RoleName roleName:RoleName.values()){
            addRole(roleName);
        }
    }
    public ResponseEntity<String> updateuserrole (String roleName, Long idUser){
        Role userRole = roleRepository.findByName(RoleName.valueOf(roleName.trim()))
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        User userToUpdate = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));
        userToUpdate.getRoles().clear();
        userToUpdate.getRoles().add(userRole);
        userRepository.save(userToUpdate);
        return  ResponseEntity.ok().body("User Role updated successfully!");
    }
}
