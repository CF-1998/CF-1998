package com.myProject.EndOfTheJob.repository;
//权限查询接口

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myProject.EndOfTheJob.domain.authority;

public interface AuthorityRepository extends JpaRepository<authority, Integer> {
	
	@Query(value = "select a.* from users u,authority a,user_authority ua where ua.user_id=u.id and ua.authority_id=a.id and u.user_name =?1", nativeQuery = true)
	public List<authority> findAuthoritiesByUsername(String username);
}
