package com.bssd.boffice.application.controller;

import com.bssd.boffice.application.dto.UserDto;
import com.bssd.boffice.application.dto.request.PageUserRequest;
import com.bssd.boffice.application.dto.request.UserRequest;
import com.bssd.boffice.application.dto.response.GroupResponse;
import com.bssd.boffice.application.dto.response.PageUserResponse;
import com.bssd.boffice.application.dto.response.UserResponse;
import com.bssd.boffice.application.exception.ResourceNotFoundException;
import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.model.User;
import com.bssd.boffice.application.service.GroupService;
import com.bssd.boffice.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getAllUser(){
            List<UserResponse> users = userService.findAll();
            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<UserResponse> getTutorialById(@PathVariable("id") long id) {
        UserResponse user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest user){
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    @ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class)
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id){
            return new ResponseEntity<>(userService.update(userRequest, id), HttpStatus.OK);
    }

    @GetMapping("/user-paging")
    public ResponseEntity<PageUserResponse> getAllUserDetail(
            @RequestParam int page,
            @RequestParam int size){
        PageUserRequest pageUserRequest = new PageUserRequest(page, size);
        return new ResponseEntity<>(userService.getAllUserPaging(pageUserRequest), HttpStatus.OK);
    }
}
