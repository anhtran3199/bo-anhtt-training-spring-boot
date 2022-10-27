package com.bssd.boffice.application.service;

import com.bssd.boffice.application.dto.request.PageUserRequest;
import com.bssd.boffice.application.dto.request.UserRequest;
import com.bssd.boffice.application.dto.response.PageUserResponse;
import com.bssd.boffice.application.dto.response.UserResponse;
import com.bssd.boffice.application.exception.ResourceNotFoundException;
import com.bssd.boffice.application.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    /**
     * Find all user
     * @author anhtt
     * @return
     */
    List<UserResponse> findAll();

    /**
     * Create user
     * @author anhtt
     * @param user
     * @return
     */
    UserResponse create(UserRequest user);

    /**
     * Update user
     * @author anhtt
     * @param user
     * @return
     */
    UserResponse update(UserRequest user, Long id);


    /**
     * Get all user with paging
     * @author anhtt
     * @param pageUserRequest Page size & page
     * @return
     */
    PageUserResponse getAllUserPaging(PageUserRequest pageUserRequest);

    UserResponse findUserById(Long id) throws ResourceNotFoundException;

//    List<UserDto> getUsers(int page, int size);
}
