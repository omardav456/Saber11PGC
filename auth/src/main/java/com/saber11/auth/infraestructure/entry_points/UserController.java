package com.saber11.auth.infraestructure.entry_points;


import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.usecase.UserUseCase;
import com.saber11.auth.infraestructure.driver_adapters.jpa_repository.UserData;
import com.saber11.auth.infraestructure.dto.LoginRequest;
import com.saber11.auth.infraestructure.dto.UserResponse;
import com.saber11.auth.infraestructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saber11/user")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;
    private final UserMapper userMapper;



    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserData userData,
                                                 @RequestHeader(value = "admincedula", required = false) String adminCedula) {
        User userOK = userUseCase.saveUser(userMapper.toUser(userData), adminCedula);

        if(userOK.getCedula() !=null){
            return new ResponseEntity<UserResponse>(userMapper.toUserResponse(userOK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/update")
    public ResponseEntity<User> actuUser(@RequestBody UserData userData,
                                          @RequestHeader(value = "admincedula", required = false) String adminCedula){

        User userActu = userUseCase.actuUser(userMapper.toUser(userData), adminCedula);

        return new ResponseEntity<>(userActu, HttpStatus.OK);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<UserResponse> searchUser(@PathVariable String cedula){

        User userFound = userUseCase.searchUserCC(cedula);

        if(userFound != null && userFound.getCedula() != null){
            return new ResponseEntity<>(userMapper.toUserResponse(userFound), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cedula){

        userUseCase.deleteUserCC(cedula);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        String answer = userUseCase.login(
                loginRequest.getCorreo(),
                loginRequest.getPassword()
        );

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}

