package com.zerohero.RestApis.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerohero.RestApis.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
