package com.Blog.Blog.service.serviceImpl;

import com.Blog.Blog.entity.User;
import com.Blog.Blog.exceptions.ResourceAlreadyExist;
import com.Blog.Blog.exceptions.ResourceNotFound;
import com.Blog.Blog.payloads.UserDto;
import com.Blog.Blog.repository.UserRepo;
import com.Blog.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepo.existsByEmail(userDto.getEmail())){
            throw new ResourceAlreadyExist("Email");
        }
        User user=this.dtoToUser(userDto);
        User user1=this.userRepo.save(user);
        return userToDto(user1);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user=this.dtoToUser(userDto);
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User user1=this.userRepo.save(user);
        return userToDto(user1);
    }

    @Override
    public void deleteUser(Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User","id",id));
        this.userRepo.delete(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(this::userToDto).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User","id",id));
        return userToDto(user);
    }
    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
