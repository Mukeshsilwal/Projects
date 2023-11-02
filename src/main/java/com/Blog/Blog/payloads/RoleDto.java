package com.Blog.Blog.payloads;

import com.Blog.Blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private int id;
    private String name;
    private UserDto user;
}
