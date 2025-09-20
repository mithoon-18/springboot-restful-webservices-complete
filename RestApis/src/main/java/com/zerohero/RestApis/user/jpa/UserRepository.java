package com.zerohero.RestApis.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerohero.RestApis.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
