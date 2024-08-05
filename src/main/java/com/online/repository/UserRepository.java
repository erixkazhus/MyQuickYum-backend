package com.online.repository;

import com.online.model.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEnt, Long> {

    public UserEnt findByEmail(String username);
}
