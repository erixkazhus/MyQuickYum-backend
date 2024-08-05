package com.online.service;

import com.online.config.JwtProvider;
import com.online.model.UserEnt;
import com.online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public UserEnt findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        UserEnt user = findUserByEmail(email);
        return user;
    }

    @Override
    public UserEnt findUserByEmail(String email) throws Exception {
        UserEnt user = userRepository.findByEmail(email);

        if (user == null){
            throw new Exception("user not found");
        }

        return user;
    }
}
