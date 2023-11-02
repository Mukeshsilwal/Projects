package com.Blog.Blog.service.serviceImpl;

import com.Blog.Blog.entity.Comment;
import com.Blog.Blog.entity.Post;
import com.Blog.Blog.exceptions.ResourceNotFound;
import com.Blog.Blog.payloads.CommentDto;
import com.Blog.Blog.repository.CommentRepo;
import com.Blog.Blog.repository.PostRepo;
import com.Blog.Blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    private final PostRepo postRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment=this.dtoToComment(commentDto);
        Comment comment1=this.commentRepo.save(comment);
        return commentToDto(comment1);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer id) {
        Comment comment=this.dtoToComment(commentDto);
        comment.setContent(commentDto.getContent());
        comment.setDate(commentDto.getDate());
        Comment comment1=this.commentRepo.save(comment);
        return commentToDto(comment1);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment=this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Comment","id",id));
        this.commentRepo.delete(comment);

    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments=this.commentRepo.findAll();
        List<CommentDto> commentDtos=comments.stream().map(comment -> this.commentToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentById(Integer id) {
        Comment comment=this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Comment","id",id));
        return commentToDto(comment);
    }

    @Override
    public CommentDto createCommentForPost(CommentDto commentDto, Integer id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        Comment comment=this.dtoToComment(commentDto);
        Comment comment1=this.commentRepo.save(comment);
        return commentToDto(comment1);
    }

    public Comment dtoToComment(CommentDto commentDto){
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        return comment;
    }
    public CommentDto commentToDto(Comment comment){
        CommentDto commentDto=this.modelMapper.map(comment,CommentDto.class);
        return commentDto;
    }
}
