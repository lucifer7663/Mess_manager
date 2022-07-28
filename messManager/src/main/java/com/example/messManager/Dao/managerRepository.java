package com.example.messManager.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.messManager.entities.Manager;

public interface managerRepository extends JpaRepository<Manager, Integer>{
	
	@Query("select u from Manager u where u.email = :email")
	public Manager getUserByUserName(@Param("email") String email);
}
