package com.test.COCONSULT.ServiceIMP;



import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Entity.UserPrinciple;
import com.test.COCONSULT.Reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username)));

        return UserPrinciple.build(user);
    }
}

