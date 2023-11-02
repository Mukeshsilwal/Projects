package com.Blog.Blog.service.serviceImpl;

import com.Blog.Blog.entity.Post;
import com.Blog.Blog.exceptions.ResourceNotFound;
import com.Blog.Blog.payloads.PostDto;
import com.Blog.Blog.repository.PostRepo;
import com.Blog.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=this.dtoToPost(postDto);
        Post post1=this.postRepo.save(post);
        return postToDto(post1);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post=this.dtoToPost(postDto);
        post.setAuthor(postDto.getAuthor());
        post.setTitle(post.getTitle());
        post.setContent(postDto.getContent());
        Post post1=this.postRepo.save(post);
        return postToDto(post1);
    }

    @Override
    public void deletePost(Integer id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts=this.postRepo.findAll();
        List<PostDto> postDtos=posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post=this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post","id",id));
        return postToDto(post);
    }
    public Post dtoToPost(PostDto postDto){
        Post post=this.modelMapper.map(postDto,Post.class);
        return post;
    }
    public PostDto postToDto(Post post){
        PostDto postDto=this.modelMapper.map(post,PostDto.class);
        return postDto;
    }
}
