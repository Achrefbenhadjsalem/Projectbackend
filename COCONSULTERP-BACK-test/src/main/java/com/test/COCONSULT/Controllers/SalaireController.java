package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Salaire;
import com.test.COCONSULT.ServiceIMP.SalaireServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/salaire")
public class SalaireController {

    @Autowired
    SalaireServiceIMP salaireServiceIMP;

    @PostMapping("/add-salaire")
    public void addSalaire(@RequestBody Salaire s ) {
        salaireServiceIMP.addSalaire(s);
    }

    @GetMapping("/get-salaire/{idSalaire}")
    public Salaire getSalaireById(@PathVariable("idSalaire") int idSalaire) {
        return salaireServiceIMP.getSalaireById(idSalaire);
    }

    @DeleteMapping("/delete-salaire/{idSalaire}")
    public void deleteSalaire(@PathVariable("idSalaire") int idSalaire) {
        salaireServiceIMP.deleteSalaire(idSalaire);
    }

    @PutMapping("/update-salaire")
    public void updateSalaire(@RequestBody Salaire s) {
        salaireServiceIMP.updateSalaire(s);
    }

    @GetMapping("/get-salaire-by-user/{idUser}")
    public List<Salaire> getSalaireByUser(
           @PathVariable("idUser") Long  idUser) {
        return salaireServiceIMP.getSalaireByUser(idUser);
    }

    @GetMapping("/getall-salaire")
    public List<Salaire> getAllSalaire() {
        return salaireServiceIMP.get();
    }



}
