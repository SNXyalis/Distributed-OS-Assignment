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

import com.example.demo.entity.Kateuthinsh;
import com.example.demo.repository.KateuthinshRepository;



@RestController
public class KateuthinshController {
	
	@Autowired
	private KateuthinshRepository kateuthinshRepository;
	
	@GetMapping("/kateuthinsh")
	public List<Kateuthinsh> retrieveAllKateuthinsh() {
		return kateuthinshRepository.findAll();
	}
	
	@GetMapping("/kateuthinsh/{id}")
	public Kateuthinsh retrieveKateuthinsh(@PathVariable int id) throws InstanceNotFoundException {
		Optional<Kateuthinsh> kateuthinsh = kateuthinshRepository.findById(id);

		if (!kateuthinsh.isPresent())
			throw new InstanceNotFoundException("id-" + id);

		return kateuthinsh.get();
	}
	
	@DeleteMapping("/kateuthinsh/{id}")
	@Secured("ROLE_ADMIN")

	public void deleteKateuthinsh(@PathVariable int id) {
		kateuthinshRepository.deleteById(id);
	}

	@PostMapping("/kateuthinsh")
	@Secured("ROLE_ADMIN")

	public ResponseEntity<Object> createKateuthinsh(@RequestBody Kateuthinsh kateuthinsh) {
		Kateuthinsh savedKateuthinsh = kateuthinshRepository.save(kateuthinsh);
		System.out.println("kateuthinsh id " + savedKateuthinsh.getId());

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedKateuthinsh.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/kateuthinsh/{id}")
	@Secured("ROLE_ADMIN")

	public ResponseEntity<Object> updateKateuthinsh(@RequestBody Kateuthinsh kateuthinsh, @PathVariable int id) {

		Optional<Kateuthinsh> KateuthinshOptional = kateuthinshRepository.findById(id);

		if (!KateuthinshOptional.isPresent())
			return ResponseEntity.notFound().build();

		kateuthinsh.setId(id);
		
		kateuthinshRepository.save(kateuthinsh);

		return ResponseEntity.noContent().build();
	}
	

}