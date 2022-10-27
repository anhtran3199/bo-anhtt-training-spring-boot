package com.bssd.boffice.application.service.impl;

import com.bssd.boffice.application.dto.LoginDto;
import com.bssd.boffice.application.dto.request.LoginRequest;
import com.bssd.boffice.application.model.User;
import com.bssd.boffice.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
        LoginDto loginDto = LoginRequest.convert(userRepository.findByUsername(username));
        if(loginDto == null){
            throw new UsernameNotFoundException("Khong tim thay user");
        }
        return new org.springframework.security.core.userdetails.User(loginDto.getUsername(), loginDto.getPassword(), new ArrayList<>());
    }
}
