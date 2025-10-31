package com.ecommerce.demo_users.web.controller.dto.mapper;

import com.ecommerce.demo_users.entity.User;
import com.ecommerce.demo_users.web.controller.dto.UserCreateDTO;
import com.ecommerce.demo_users.web.controller.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserCreateDTO userCreateDto){
        return new ModelMapper().map(userCreateDto, User.class);
    }

    public static UserResponseDTO toDto(User user){
        String role = user.getRole().name().substring("ROLE_".length()).toLowerCase();
        PropertyMap<User, UserResponseDTO> props = new PropertyMap<User, UserResponseDTO>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> toListDto(List<User> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toUnmodifiableList());
    }

}
