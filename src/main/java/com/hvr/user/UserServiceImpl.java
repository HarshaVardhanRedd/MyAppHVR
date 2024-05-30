package com.hvr.user;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserData> getUserById(long id){
		return userRepository.findById(id);
	}
	
	public UserData saveUser(UserData user) {
		logger.info("user name : "+ user.getUserName());
		return userRepository.save(user);
	}
	
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				//UserDetails user = userRepository.findByUserName(username);
				 UserData userEntity = userRepository.findByUserName(username);

			        if (userEntity == null) {
			            throw new UsernameNotFoundException("User not found with username: " + username);
			        }
			        logger.error("user name:"+userEntity.getUserName());
			        logger.error("user name:"+userEntity.getPassword());
			        return new org.springframework.security.core.userdetails.User(
			                userEntity.getUserName(),
			                userEntity.getPassword(),
			                new ArrayList<>()
			        );
			}
		};
	}
}
