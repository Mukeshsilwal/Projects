package com.Blog.Blog.repository;

import com.Blog.Blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
