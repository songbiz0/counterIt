package com.counterit.component;

import com.counterit.model.UserEntity;
import com.counterit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public int join(UserEntity entity) {
        entity.setUpw(passwordEncoder.encode(entity.getUpw()));
        int result = 0;
        try {
            entity.setUrole("ROLE_USER");
            userRepository.save(entity);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
