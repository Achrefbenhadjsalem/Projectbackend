package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Pointage;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Interfaces.PointageServiceInterface;
import com.test.COCONSULT.Reposotories.PointageRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class PointageServiceIMP implements PointageServiceInterface {

    @Autowired
    private PointageRepository pointageRepository;

    @Autowired
    private UserRepository userRepository;

    public void addPointageForUser(long userId) {
        // Create a new Pointage instance
        Pointage pointage = new Pointage();
        pointage.setDateEntr(Date.from(Instant.now())); // Set the current time as the entry date
        // Retrieve the User entity by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        pointage.setUser(user);

        // Get or initialize the pointages set
        Set<Pointage> setPointages = user.getPointages();
        if (setPointages == null) {
            setPointages = new HashSet<>();
        }

        // Add the new pointage to the set
        setPointages.add(pointage);
        user.setPointages(setPointages);

        // Save the pointage to pointage repository
        pointageRepository.save(pointage);
        // Save the updated user to user repository
        userRepository.save(user);
    }

    @Override
    public void deletePointage(int idPointage) {
        pointageRepository.deleteById(idPointage);
    }

    @Override
    public Pointage updatePointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    @Override
    public Pointage getPointageById(int idPoint) {
        return pointageRepository.findById(idPoint).get();
    }
}
