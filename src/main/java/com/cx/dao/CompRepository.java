package com.cx.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cx.entity.Comp;

public interface CompRepository extends JpaRepository<Comp, Long> {
	
}