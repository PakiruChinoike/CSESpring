package com.io.github.pakiruchinoike.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.pakiruchinoike.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
