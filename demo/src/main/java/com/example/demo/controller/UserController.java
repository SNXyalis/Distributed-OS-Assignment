package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;




@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	@Secured("ROLE_ADMIN")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	@Secured("ROLE_ADMIN")
	public User retrieveUser(@PathVariable int id) throws ResponseStatusException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);

		return user.get();
	}
	
	
	@DeleteMapping("/users/{id}")
	@Secured("ROLE_ADMIN")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	 @Bean
	    public PasswordEncoder passwdEncoder() {
	        PasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder;
	    }

	
	@PostMapping("/users")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
	    

	    user.setPasswd(passwdEncoder().encode(user.getPasswd()));

		User savedUser = userRepository.save(user);
		System.out.println("user id " + savedUser.getId());

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
     
	}
	
	
	
	@PutMapping("/users/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	

}