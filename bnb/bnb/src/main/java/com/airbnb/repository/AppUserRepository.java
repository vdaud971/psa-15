package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByUserName(String userName);

    Optional<AppUser>findByUserNameOrEmail(String userName,String email );
} 