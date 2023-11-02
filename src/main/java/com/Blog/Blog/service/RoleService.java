package com.Blog.Blog.service;

import com.Blog.Blog.payloads.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Integer id);

    RoleDto createRoleForUser(RoleDto roleDto, Integer id);

    void deleteRole(Integer id);

    List<RoleDto> getAllRole();

    RoleDto getRoleById(Integer id);

}
