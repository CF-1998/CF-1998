package com.myProject.EndOfTheJob.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myProject.EndOfTheJob.domain.comments;


@Repository
public interface commentsRepository  extends JpaRepository<comments, Integer>{

    public void deleteByBooksId(int id);
	
	public Page<comments> findByBooksId(int id,Pageable pageable);
	
	@Query (value="SELECT  * FROM  comments ORDER BY id  DESC LIMIT ?1",nativeQuery = true)
	public List<comments> getCurrentComments(int limit);
	
	//分页查询
	public Page<comments> findAll(Pageable pageable);
}
