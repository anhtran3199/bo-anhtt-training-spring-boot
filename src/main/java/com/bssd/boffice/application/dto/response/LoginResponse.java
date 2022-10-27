package com.bssd.boffice.application.dto.response;

import com.bssd.boffice.application.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


public class LoginResponse {

	private final String jwtToken;

	public LoginResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken(){
		return this.jwtToken;
	}
}
