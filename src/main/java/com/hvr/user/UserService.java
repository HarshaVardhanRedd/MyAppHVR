package com.hvr.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

	UserDetailsService userDetailsService();
	UserData saveUser(UserData user);

}
