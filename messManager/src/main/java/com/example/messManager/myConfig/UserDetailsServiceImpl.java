package com.example.messManager.myConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.messManager.Dao.managerRepository;
import com.example.messManager.Dao.memberRepository;
import com.example.messManager.entities.Manager;
import com.example.messManager.entities.Member;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private managerRepository repository; 
	
	@Autowired
	private memberRepository repository_mem;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching from database
		Manager manager = 	repository.getUserByUserName(username);
		
		Member member = 	repository_mem.getUserByUserName(username);
		
		UserDetails userDetails = null;
		
		if (manager == null && member == null) {
			throw new UsernameNotFoundException("couldn't found any user");
			
		}else if (manager != null && member == null) {
			userDetails = new customUserDetailsManager(manager);
			
		}
		else if (manager == null && member != null) {
			userDetails = new customUserDetailsMember(member);
		}
		
		return userDetails;
		
	}

}
