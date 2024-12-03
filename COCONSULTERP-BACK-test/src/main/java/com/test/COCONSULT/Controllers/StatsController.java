package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.ServiceIMP.StatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/Stats")
public class StatsController {
    @Autowired
    private StatServiceImp statServiceImp;


    @GetMapping("/usersPerDay")
    public ResponseEntity<Map<LocalDate, Long>> getUsersPerDay() {
        return ResponseEntity.ok(statServiceImp.getUsersPerDay());
    }

    @GetMapping("/usersByRoles")
    public ResponseEntity<Map<String, Long>> getUsersByRoles() {
        return ResponseEntity.ok(statServiceImp.getUsersByRoles());
    }
}
