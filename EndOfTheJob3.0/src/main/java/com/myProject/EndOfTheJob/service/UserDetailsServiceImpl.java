package com.myProject.EndOfTheJob.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myProject.EndOfTheJob.domain.Users;
import com.myProject.EndOfTheJob.domain.authority;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private userService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Users users = userService.getUsers(username);
	List<authority> authoritys = userService.getUserAuthority(username);
	List<SimpleGrantedAuthority> list = authoritys.stream().map(authority ->new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
	if (users!=null) {
		UserDetails userDetails = 
				new User(users.getUserName(), users.getPassword(), list);
		return userDetails;
	}else {
		throw new UsernameNotFoundException("当前用户不存在!");
	}
	}

}
