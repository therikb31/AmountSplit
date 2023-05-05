package com.globe.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globe.model.AppUser;

public interface AppUserDAO extends JpaRepository<AppUser, String>{

}
