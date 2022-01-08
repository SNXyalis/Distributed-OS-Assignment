package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.management.InstanceNotFoundException;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Authorities;
import com.example.demo.repository.AuthoritiesRepository;




@RestController
public class AuthoritiesController {
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@GetMapping("/authorities")
	@Secured("ROLE_ADMIN")

	public List<Authorities> retrieveAllAuthorities() {
		return authoritiesRepository.findAll();
	}
	
	
	@GetMapping("/authorities/{id}")
	@Secured("ROLE_ADMIN")

	public Authorities retrieveAuthorities(@PathVariable int id) throws InstanceNotFoundException {
		Optional<Authorities> authorities = authoritiesRepository.findById(id);

		if (!authorities.isPresent())
			throw new InstanceNotFoundException("id-" + id);

		return authorities.get();
	}
	
	@DeleteMapping("/authorities/{id}")
	@Secured("ROLE_ADMIN")

	public void deleteAuthorities(@PathVariable int id) {
		authoritiesRepository.deleteById(id);
	}

	@PostMapping("/authorities")
	@Secured("ROLE_ADMIN")

	public ResponseEntity<Object> createAuthorities(@RequestBody Authorities authorities) {
		Authorities savedAuthorities = authoritiesRepository.save(authorities);
		System.out.println("authorities id " + savedAuthorities.getId());

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAuthorities.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/authorities/{id}")
	@Secured("ROLE_ADMIN")

	public ResponseEntity<Object> updateAuthorities(@RequestBody Authorities authorities, @PathVariable int id) {

		Optional<Authorities> authoritiesOptional = authoritiesRepository.findById(id);

		if (!authoritiesOptional.isPresent())
			return ResponseEntity.notFound().build();

		authorities.setId(id);
		
		authoritiesRepository.save(authorities);

		return ResponseEntity.noContent().build();
	}
	

}