package com.Blog.Blog.service.serviceImpl;

import com.Blog.Blog.entity.Role;
import com.Blog.Blog.entity.User;
import com.Blog.Blog.exceptions.ResourceNotFound;
import com.Blog.Blog.payloads.RoleDto;
import com.Blog.Blog.repository.RoleRepo;
import com.Blog.Blog.repository.UserRepo;
import com.Blog.Blog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    @Override
    public RoleDto createRoleForUser(RoleDto roleDto, Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFound("User","id",id));
        Role role=this.dtoToRole(roleDto);
        role.setUser(user);
        Role role1=this.roleRepo.save(role);
        return roleToDto(role1);
    }



    @Override
    public void deleteRole(Integer id) {
        Role role=this.roleRepo.findById(id).orElseThrow();
        this.roleRepo.delete(role);

    }

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role=this.roleRepo.findById(id).orElseThrow();
        return roleToDto(role);
    }

    @Override
    public List<RoleDto> getAllRole() {
        List<Role> roles=this.roleRepo.findAll();
        List<RoleDto> roleDtos=roles.stream().map((role)->this.roleToDto(role)).collect(Collectors.toList());
        return roleDtos;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role=this.dtoToRole(roleDto);
        Role role1=this.roleRepo.save(role);
        return roleToDto(role1);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Integer id) {
        Role role=this.dtoToRole(roleDto);
        role.setName(roleDto.getName());
        Role role1=this.roleRepo.save(role);
        return roleToDto(role1);
    }

    private Role dtoToRole(RoleDto roleDto){
        Role role=this.modelMapper.map(roleDto, Role.class);
        return role;
    }
    private RoleDto roleToDto(Role role){
        RoleDto roleDto=this.modelMapper.map(role,RoleDto.class);
        return roleDto;
    }

}