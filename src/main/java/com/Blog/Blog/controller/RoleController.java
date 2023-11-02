package com.Blog.Blog.controller;

import com.Blog.Blog.exceptions.ApiResponse;
import com.Blog.Blog.payloads.RoleDto;
import com.Blog.Blog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/get")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        List<RoleDto> roleDtos=this.roleService.getAllRole();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> getUserById(@PathVariable Integer id){
        RoleDto roleDto=this.roleService.getRoleById(id);
        return new ResponseEntity<>(roleDto,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        RoleDto roleDto1=this.roleService.createRole(roleDto);
        return new ResponseEntity<>(roleDto1,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto,@PathVariable Integer id){
        RoleDto roleDto1=this.roleService.updateRole(roleDto,id);
        return new ResponseEntity<>(roleDto1,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer id){
        this.roleService.deleteRole(id);
        return new ResponseEntity<>(new ApiResponse("Role has been deleted",HttpStatus.OK),HttpStatus.OK);
    }
    @PostMapping("/post1/{id}")
    public ResponseEntity<RoleDto> createRoleForUser(@RequestBody RoleDto roleDto,@PathVariable Integer id){
        RoleDto roleDto1=this.roleService.createRoleForUser(roleDto,id);
        return new ResponseEntity<>(roleDto1,HttpStatus.CREATED);
    }
}
