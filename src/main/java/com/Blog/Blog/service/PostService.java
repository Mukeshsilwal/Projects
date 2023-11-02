package com.Blog.Blog.service;

import com.Blog.Blog.payloads.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto updatePost(PostDto postDto,Integer id);
    void deletePost(Integer id);
    List<PostDto> getAllPost();
    PostDto getPostById(Integer id);
//    PostDto createPostWithThumbnails(PostDto postDto,)
}
