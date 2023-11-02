package com.Blog.Blog.controller;

import com.Blog.Blog.entity.Comment;
import com.Blog.Blog.exceptions.ApiResponse;
import com.Blog.Blog.payloads.CommentDto;
import com.Blog.Blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/get")
    public ResponseEntity<List<CommentDto>> getAllComment(){
        List<CommentDto> comments=this.commentService.getAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CommentDto> getCommentId(@PathVariable Integer id){
        CommentDto commentDto=this.commentService.getCommentById(id);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        CommentDto commentDto1=this.commentService.createComment(commentDto);
        return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);
    }
    @PostMapping("/post/{id}")
    public ResponseEntity<CommentDto> createCommentForPost(@RequestBody CommentDto commentDto,@PathVariable Integer id){
        CommentDto commentDto1=this.commentService.createCommentForPost(commentDto,id);
        return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer id){
        CommentDto commentDto1=this.commentService.updateComment(commentDto,id);
        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        this.commentService.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Comment has been deleted",HttpStatus.OK),HttpStatus.OK);
    }
}
