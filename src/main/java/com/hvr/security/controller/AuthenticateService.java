package com.hvr.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hvr.security.services.JwtUtil;
import com.hvr.signup.SignInRequestData;
import com.hvr.signup.SignInResponseData;
import com.hvr.signup.SignUpRequestData;
import com.hvr.signup.SignUpResponseData;
import com.hvr.user.UserData;
import com.hvr.user.UserRepository;
import com.hvr.user.UserService;

@Service
public class AuthenticateService {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder pasEncoder;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticateService.class);

	public SignUpResponseData addUser(SignUpRequestData reqData) {
		SignUpResponseData resData = new SignUpResponseData();
		if (reqData != null) {
			UserData user = new UserData();
			user.setDisplayName(reqData.getDisplayName());
			user.seteMail(reqData.geteMail());
			user.setUserName(reqData.getUserName());
			user.setPassword(pasEncoder.encode(reqData.getPassword()));
			userService.saveUser(user);
			resData.setMsg("Success");
		}
		return resData;
	}

	public SignInResponseData signInRequest(SignInRequestData signInReqData) {
		SignInResponseData signInResData = new SignInResponseData();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			String userName = signInReqData.getUserName();
			logger.info("userName: " + userName);
			String password = signInReqData.getPassword();
			logger.info("password: " + password);
			// logger.info("password: "+ passwordEncoder.encode(password));
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			var user = userRepository.findByUserName(signInReqData.getUserName());
			if (user != null) {
				var jwtToken = jwtUtil.generateToken(user.getUserName());
				//var refreshJwtToken = jwtUtil.generateToken(signInReqData.getUserName());
				signInResData.setToken(jwtToken);
				signInResData.setAuthention(true); 
				
				return signInResData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
