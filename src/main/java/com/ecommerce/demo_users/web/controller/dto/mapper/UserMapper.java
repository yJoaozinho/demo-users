package com.ecommerce.demo_users.web.controller.dto.mapper;

import com.ecommerce.demo_users.entity.User;
import com.ecommerce.demo_users.web.controller.dto.UserCreateDto;
import com.ecommerce.demo_users.web.controller.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserCreateDto userCreateDto){
        return new ModelMapper().map(userCreateDto, User.class);
    }

    public static UserResponseDto toDto(User user){
        String role = user.getRole().name().substring("ROLE_".length()).toLowerCase();
        PropertyMap<User, UserResponseDto> props = new PropertyMap<User, UserResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toUnmodifiableList());
    }

}
