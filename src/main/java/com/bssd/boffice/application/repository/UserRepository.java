package com.bssd.boffice.application.repository;

import com.bssd.boffice.application.dto.UserDto;
import com.bssd.boffice.application.dto.response.PageUserResponse;
import com.bssd.boffice.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUsername(@Param("name") String username);

    //filer by email and phone created_date desc

    @Query("SELECT u FROM User u WHERE u.email LIKE %:email% ORDER BY u.createdDate DESC")
    List<User> findUserByEmailLike(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.phone LIKE %:phone% ORDER BY u.createdDate DESC")
    List<User> findUserByPhoneLike(@Param("phone") String phone);


}