package com.quinobo.jack.service;

import org.springframework.stereotype.Service;

import com.quinobo.jack.util.Constants;

@Service
public class AdminService implements Constants{

	public String adminLogin(String id, String pw) {
		for (String[] strings : ADMINACCOUNT) {
			if(strings[0].equals(id) && "Wjfejfk".equals(pw)) {
				return strings[1]; 
			};
		}
		return null;
		
	}
	
}
