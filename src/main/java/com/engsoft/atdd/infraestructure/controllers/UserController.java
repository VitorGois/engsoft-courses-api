package com.engsoft.atdd.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engsoft.atdd.application.usecases.ListUsersUseCase;
import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.infraestructure.controllers.dtos.ListUsersReadDto;
import com.engsoft.atdd.infraestructure.controllers.dtos.UserDto;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ListUsersUseCase listUsersUseCase;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email
    ) {
        ListUsersReadDto params = new ListUsersReadDto(name, email);

        try {
            List<User> users = listUsersUseCase.execute(params);
            
            List<UserDto> userDtos = users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail().getValue()))
                .collect(Collectors.toList());
    
            return ResponseEntity.ok(userDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}