package com.test.COCONSULT.Controllers;



import com.test.COCONSULT.DTO.ResetPass;
import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Entity.Role;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.UserServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

@Autowired
UserServiceIMP userServiceIMP;


    @GetMapping("/list-user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> ListUser() {
        return userServiceIMP.getAllUser();
    }



    @PutMapping("/validate-user/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public void validInscription(@PathVariable("idUser") Long idUser) {
        userServiceIMP.validInscription(idUser);
    }
    @PutMapping("/debloque-user/{idUser}")
    public void debloqueUser(@PathVariable("idUser") Long idUser) {
        userServiceIMP.debloqueUser(idUser);
    }

    @PutMapping("/bloque-user/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public void bloqueUser(@PathVariable("idUser") Long idUser) {
        userServiceIMP.bloqueUser(idUser);
    }
    @DeleteMapping("/delete-user/{idUser}")
    public void deleteAccount(@PathVariable("idUser") Long idUser) {
        userServiceIMP.deleteUser(idUser);
    }

    @GetMapping("/list-RolesName/{RolesName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> ListUserByRoles(@PathVariable("RolesName") RoleName roleName) {
        return userServiceIMP.getUserByRoles(roleName);
    }
    @PutMapping("forgetpass/{username}")
    public ResponseEntity<?> forgetPassword(@PathVariable("username") String username, @RequestBody ResetPass resetPass) {
       return userServiceIMP.updatePassword(username,resetPass);
    }
    @PostMapping("forgetpassword/{email}")
    public ResponseEntity<?> userForgetPassword(@PathVariable("email") String email) {
        return userServiceIMP.userforgetpassword(email);
    }
    @PutMapping("forgetpassbyemail/{email}")
    public ResponseEntity<?> forgetPasswordbyemail(@PathVariable("email") String email, @RequestBody ResetPass resetPass) {
        return userServiceIMP.updatePasswordBymail(email,resetPass);
    }
  /*  @GetMapping("/Alluserprofiles/")

    public List<User> getAlluserprofiles() {
        return userServiceIMP.getAlluserprofiles();
    }

   */
    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userServiceIMP.getUserById(id);}
    /*
    @GetMapping("/list-User/ASC")
    public List<User> getUsersOrderBySum_totalAsc() {
        return userService.getUsersOrderBySum_totalAsc();
    }

    @GetMapping("/sorted-by-role/{role}")
    public List<User> getUsersSortedByRole(@PathVariable("role") String role) {
       RoleName role1= RoleName.valueOf(role);
        return userService.getAllUserByRoleOrderSum_total(role1);
    }
    */

}
