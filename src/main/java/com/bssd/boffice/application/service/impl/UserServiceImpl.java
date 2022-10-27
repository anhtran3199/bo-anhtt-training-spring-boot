package com.bssd.boffice.application.service.impl;

import com.bssd.boffice.application.dto.request.PageUserRequest;
import com.bssd.boffice.application.dto.request.UserRequest;
import com.bssd.boffice.application.dto.response.PageUserResponse;
import com.bssd.boffice.application.dto.response.UserResponse;
import com.bssd.boffice.application.exception.ResourceNotFoundException;
import com.bssd.boffice.application.model.User;
import com.bssd.boffice.application.repository.GroupRepository;
import com.bssd.boffice.application.repository.UserRepository;
import com.bssd.boffice.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED)
    public UserResponse create(UserRequest userRequest) {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFullname(userRequest.getFullname());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setUserGroup(groupRepository.findById(userRequest.getGroupId()).get());
        user.setCreatedBy(authentication.getName());
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedBy(null);
        user.setUpdatedDate(null);
        user.setExpireDate(LocalDateTime.now().plus(365, ChronoUnit.DAYS));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserResponse.fromUser(userRepository.save(user));
    }

    @Override
    @Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED)
    public UserResponse update(UserRequest userRequest, Long id) {
        User user = userRepository.findById(id).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setFullname(userRequest.getFullname());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setUserGroup(groupRepository.findById(userRequest.getGroupId()).get());
        user.setUpdatedDate(LocalDateTime.now());
        user.setUpdatedBy(authentication.getName());
        return UserResponse.fromUser(userRepository.save(user));
    }


    @Override
    public PageUserResponse getAllUserPaging(PageUserRequest pageUserRequest) {
        Pageable pageable = PageRequest.of(pageUserRequest.getPage(), pageUserRequest.getPageSize());
        PageUserResponse pageUserResponse = PageUserResponse.fromPageUser(userRepository.findAll(pageable));

        return pageUserResponse;
    }

    @Override
    public UserResponse findUserById(Long id) throws ResourceNotFoundException{
        return UserResponse.fromUser(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id)));
    }

}
