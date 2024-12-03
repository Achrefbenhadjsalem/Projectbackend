package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Conge;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Reposotories.CongeRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.CongeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/conge")
public class CongeController {
    @Autowired
    CongeServiceImpl congeServiceIMP;

    @Autowired
    CongeRepository congeRepository;

    @Autowired
    UserRepository  userRepository;

    @PostMapping("/add-conge")
    public void addConge(@RequestBody Conge c) {
        congeServiceIMP.addConge(c);
    }

    @GetMapping("/getCurrentConge")
    public List<Conge> getCurrentConge() {
        return congeServiceIMP.getCurrentConge();
    }


    @DeleteMapping("/delete-conge/{id}")
    public void deleteConge(@PathVariable int id) {
        Conge conge = congeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid conge Id:" + id));
        User user = userRepository.findById(conge.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + conge.getUser().getId()));
        user.getConges().remove(conge);
        userRepository.save(user);
        congeRepository.deleteById(id);
    }

    @PutMapping("/edit-conge")
    public void editConge(@RequestBody Conge c) {
        congeRepository.save(c);
    }
}
