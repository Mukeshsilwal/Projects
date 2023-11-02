package com.Blog.Blog.controller;

import com.Blog.Blog.exceptions.ApiResponse;
import com.Blog.Blog.payloads.PostDto;
import com.Blog.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<List<PostDto> >getAllPost() {
        List<PostDto> postDtos = this.postService.getAllPost();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
        PostDto postDto = this.postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto postDto1 = this.postService.createPost(postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer id) {
        PostDto postDto1 = this.postService.updatePost(postDto, id);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        this.postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post has been deleted", HttpStatus.OK), HttpStatus.OK);
    }
}
