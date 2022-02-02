package com.example.demo.repository;


import com.example.demo.entity.Kateuthinsh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="kateuthinsh")
public interface KateuthinshRepository extends JpaRepository<Kateuthinsh, Integer> {
	
}