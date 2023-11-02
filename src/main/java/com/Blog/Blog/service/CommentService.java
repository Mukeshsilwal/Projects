package com.Blog.Blog.service;

import com.Blog.Blog.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto,Integer id);
    void deleteComment(Integer id);
    List<CommentDto> getAllComment();
    CommentDto getCommentById(Integer id);
    CommentDto createCommentForPost(CommentDto commentDto,Integer id);
}
