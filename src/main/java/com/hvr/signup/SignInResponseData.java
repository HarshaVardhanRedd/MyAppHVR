package com.hvr.signup;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignInResponseData {

	private String token;
	public boolean isAuthention() {
		return authention;
	}
	public void setAuthention(boolean authention) {
		this.authention = authention;
	}
	private boolean authention;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
