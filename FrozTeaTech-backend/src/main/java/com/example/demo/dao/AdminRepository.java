package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findAdminByUsername(String username);

//	@Query(value = "SELECT * FROM ADMIN u WHERE u.username = ?", nativeQuery = true)
//	public Optional<Admin> findByusername(String username);
}
