package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.ServiceIMP.RoleServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RoleController  {
@Autowired
RoleServiceIMP roleServiceIMP;

    @PostMapping ("/addRole/{RolesName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  void addRole(@PathVariable("RolesName") RoleName roleName){
        roleServiceIMP.addRole(roleName);
    }
    @DeleteMapping("/deleteRole/{RolesName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRole(@PathVariable("RolesName") RoleName roleName) {
        roleServiceIMP.deleteRole(roleName);
    }
    @GetMapping ("/getAllRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Role> getAllRoles() {
        return roleServiceIMP.getAllRoles();
    }
    @PostMapping("/AddALLRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void AddALLRoles() {
        roleServiceIMP.AddALLRoles();
    }
    @PutMapping("/updateuserrole/{roleName}/{idUser}")
    public ResponseEntity<String> updateuserrole (@PathVariable("roleName") String roleName, @PathVariable("idUser") Long idUser){
       return roleServiceIMP.updateuserrole(roleName,idUser);
    }
/*
   @PutMapping("/deleteRole/{roleName}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public void deleteRole(@PathVariable("roleName") RoleName roleName) {
    Role role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new RuntimeException("Role not found"));

    // Remove the role from all users who are currently assigned that role
    List<User> usersWithRole = userRepository.findByRolesContaining(role);
    for (User user : usersWithRole) {
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    // Delete the role
    roleRepository.delete(role);
}

    }*/

}
