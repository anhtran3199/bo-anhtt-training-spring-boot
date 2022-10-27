package com.bssd.boffice.application.dto.request;

import com.bssd.boffice.application.dto.LoginDto;
import com.bssd.boffice.application.model.User;

import java.io.Serializable;

public class LoginRequest {
	// convert this request > Dto
	public static LoginDto convert(User user) {
        LoginDto loginDto = new LoginDto(user.getUsername(), user.getPassword());
		return loginDto;
	}
}
