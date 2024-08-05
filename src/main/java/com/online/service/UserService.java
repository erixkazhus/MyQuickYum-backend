package com.online.service;

import com.online.model.UserEnt;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {
    public UserEnt findUserByJwtToken(String jwt) throws Exception;

    public UserEnt findUserByEmail(String email) throws Exception;
}
