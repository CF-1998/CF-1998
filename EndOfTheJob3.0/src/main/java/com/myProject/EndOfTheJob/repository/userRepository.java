package com.myProject.EndOfTheJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.myProject.EndOfTheJob.domain.Users;

public interface userRepository extends JpaRepository<Users, Integer> {
	@Query("select u from Users u where u.userName = ?1 and u.password =?2")
	public Users getUser(String username,String password);
	public Users findByUserName(String username);
}
