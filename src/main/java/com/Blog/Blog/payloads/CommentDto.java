package com.Blog.Blog.payloads;

import com.Blog.Blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private Date date;
    private PostDto post;
}
