package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
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

import com.example.demo.entity.Aithsh;
import com.example.demo.repository.AithshRepository;



@RestController
public class AithshController {
	
	@Autowired
	private AithshRepository aithshRepository;
	
	@GetMapping("/aithsh")
    @Secured({ "ROLE_ADMIN", "ROLE_TEACHER","ROLE_GRAMMATEIA","ROLE_STUDENT" })
    public List<Aithsh> retrieveAllAithsh() {
        return aithshRepository.findAll();
    }
	
	@GetMapping("/aithsh/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER","ROLE_GRAMMATEIA","ROLE_STUDENT" })

	public Aithsh retrieveAithsh(@PathVariable int id) throws InstanceNotFoundException {
		Optional<Aithsh> aithsh = aithshRepository.findById(id);

		if (!aithsh.isPresent())
			throw new InstanceNotFoundException("id-" + id);

		return aithsh.get();
	}
	
	@DeleteMapping("/aithsh/{id}")
	@Secured({ "ROLE_ADMIN" })

	public void deleteAithsh(@PathVariable int id) {
		aithshRepository.deleteById(id);
	}

	
	@PostMapping("/aithsh")
	@Secured({ "ROLE_ADMIN","ROLE_STUDENT" })

	public ResponseEntity<Object> createAithsh(@RequestBody Aithsh aithsh) {
		Aithsh savedAithsh = aithshRepository.save(aithsh);
		System.out.println("aithsh id " + savedAithsh.getId());

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAithsh.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/aithsh/{id}")
	@Secured({ "ROLE_ADMIN","ROLE_STUDENT", "ROLE_TEACHER","ROLE_GRAMMATEIA" })

	public ResponseEntity<Object> updateAithsh(@RequestBody Aithsh aithsh, @PathVariable int id) {

		Optional<Aithsh> AithshOptional = aithshRepository.findById(id);

		if (!AithshOptional.isPresent())
			return ResponseEntity.notFound().build();

		aithsh.setId(id);
		
		aithshRepository.save(aithsh);

		return ResponseEntity.noContent().build();
	}
	

}