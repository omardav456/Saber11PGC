package com.saber11.auth.infraestructure.mapper;

import com.saber11.auth.domain.model.User;
import com.saber11.auth.infraestructure.driver_adapters.jpa_repository.UserData;
import com.saber11.auth.infraestructure.dto.UserProfileResponse;
import com.saber11.auth.infraestructure.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserData toUserData(User user){
       return new UserData(
               user.getCedula(),
               user.getNombre(),
               user.getCorreo(),
               user.getPassword(),
               user.getRol()
       );
    }

    public User toUser(UserData userData){
        return new User(
                userData.getCedula(),
                userData.getNombre(),
                userData.getCorreo(),
                userData.getPassword(),
                userData.getRol()

        );
    }

    public UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getNombre(),
                user.getCorreo(),
                user.getRol()
        );
    }

    public UserProfileResponse toUserProfileResponse(User user){
        return new UserProfileResponse(
                user.getCedula(),
                user.getNombre(),
                user.getCorreo(),
                user.getRol()
        );
    }

    public List<UserProfileResponse> toUserProfileResponseList(List<User> users){
        return users.stream()
                .map(this::toUserProfileResponse)
                .collect(Collectors.toList());
    }
}
