package com.counterit.security;

import com.counterit.model.UserEntity;
import com.counterit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    @Autowired
    UserRepository userRepository;

    public UserEntity getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUid(auth.getName());
    }

    public Long getLoginUserPk() {
        return getLoginUser().getIdx();
    }
}