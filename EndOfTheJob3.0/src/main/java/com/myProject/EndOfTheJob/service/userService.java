package com.myProject.EndOfTheJob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.EndOfTheJob.domain.Users;
import com.myProject.EndOfTheJob.domain.authority;
import com.myProject.EndOfTheJob.domain.user_authority;
import com.myProject.EndOfTheJob.repository.AuthorityRepository;
import com.myProject.EndOfTheJob.repository.userRepository;
import com.myProject.EndOfTheJob.repository.user_authorityRepository;

@Transactional
@Service

public class userService {
	@Autowired
	public userRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
    private user_authorityRepository user_authorityRepository;
//	验证登录信息
	public Users checking(String username, String password) {
		return userRepository.getUser(username, password);
	}

//保存新注册的用户
	public Users save(Users user) {
		return userRepository.save(user);
	}
//给新用户默认权限
	public user_authority save(user_authority ua) {
		return user_authorityRepository.save(ua);
	}

//根据用户名得到用户信息
	public Users findByName(String username) {
		Users users = userRepository.findByUserName(username);
		return users;
	}

	// 账号安全管理(结合redis缓存进行用户管理)
	public Users getUsers(String username) {
		Users users = null;
		Object o = redisTemplate.opsForValue().get("user_" + username);
		if (o != null) {
			users = (Users) o;
		} else {
			users = userRepository.findByUserName(username);
			if (users != null) {
				redisTemplate.opsForValue().set("user_" + username, users);
			}
		}
		return users;
	}
	//使用唯一用户名查询用户权限
	 public List<authority> getUserAuthority(String username){
	    	List<authority> authorities = null;
	        Object o = redisTemplate.opsForValue().get("authorities_"+username);
	        if (o!=null) {
				authorities=(List<authority>)o;
			}else {
				authorities=authorityRepository.findAuthoritiesByUsername(username);
				if(authorities.size()>0) {
					redisTemplate.opsForValue().set("authorities_"+username,authorities);
				}
			}
	        return authorities;
	    }
}
