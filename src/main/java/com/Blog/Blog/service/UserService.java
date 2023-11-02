package com.Blog.Blog.service;

import com.Blog.Blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);
    void deleteUser(Integer id);
    List<UserDto> getAllUser();
    UserDto getUserById(Integer id);
}
