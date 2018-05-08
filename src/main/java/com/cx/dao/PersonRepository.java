package com.cx.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cx.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	public Person findByPersonName(String personName);
	
}