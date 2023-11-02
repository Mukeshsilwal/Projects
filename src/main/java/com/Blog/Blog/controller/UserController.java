package com.Blog.Blog.controller;

import com.Blog.Blog.exceptions.ApiResponse;
import com.Blog.Blog.payloads.UserDto;
import com.Blog.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDto=this.userService.getAllUser();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto userDto=this.userService.getUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1=this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer id){
        UserDto userDto1=this.userService.updateUser(userDto,id);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User is removed",HttpStatus.OK),HttpStatus.OK);
    }
}
