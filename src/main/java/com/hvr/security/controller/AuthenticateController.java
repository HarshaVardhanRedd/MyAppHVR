package com.hvr.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hvr.signup.SignInRequestData;
import com.hvr.signup.SignInResponseData;
import com.hvr.signup.SignUpRequestData;
import com.hvr.signup.SignUpResponseData;

@RestController
@RequestMapping("/hvr/auth")
public class AuthenticateController {
	@Autowired
	private AuthenticateService authService;

	@PostMapping("/signUp")
	public ResponseEntity<SignUpResponseData> signUp(@RequestBody SignUpRequestData reqData){
		return ResponseEntity.ok(authService.addUser(reqData));
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<SignInResponseData> signIn(@RequestBody SignInRequestData reqData){
		return ResponseEntity.ok(authService.signInRequest(reqData));
	}
}
