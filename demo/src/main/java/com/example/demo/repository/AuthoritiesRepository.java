package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Authorities;



@RepositoryRestResource(path="authorities")
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
	
}