package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class StatServiceImp {

        @Autowired
        private UserRepository userRepository;

        public Map<LocalDate, Long> getUsersPerDay() {
            return userRepository.countUsersPerDay().stream()
                    .collect(Collectors.toMap(
                            data -> (LocalDate) data[0],
                            data -> (Long) data[1]
                    ));
        }

        public Map<String, Long> getUsersByRoles() {
            return userRepository.countUsersByRoles().stream()
                    .collect(Collectors.toMap(
                            data -> ((RoleName) data[0]).name(),
                            data -> (Long) data[1]
                    ));
        }
}
