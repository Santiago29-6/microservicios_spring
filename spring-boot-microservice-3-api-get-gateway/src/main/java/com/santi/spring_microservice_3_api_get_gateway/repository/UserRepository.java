package com.santi.spring_microservice_3_api_get_gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.santi.spring_microservice_3_api_get_gateway.model.Role;
import com.santi.spring_microservice_3_api_get_gateway.model.User;

import feign.Param;


public interface UserRepository  extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User SET role=:role WHERE username=:username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);


}