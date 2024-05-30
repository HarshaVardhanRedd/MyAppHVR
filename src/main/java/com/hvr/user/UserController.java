package com.hvr.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/saveUser")
	public void insertUserDetails(@RequestBody UserData user) {
		logger.info("User :"+ user.getDisplayName());
		logger.info("user mail:"+ user.geteMail());
		userService.saveUser(user);
	}
	
	@GetMapping("/getUser")
	public Optional<UserData> getUserById(@RequestParam long id) {
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam long id) {
        logger.info("Deleting user with id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
