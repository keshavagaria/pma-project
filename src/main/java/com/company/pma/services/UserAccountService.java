package com.company.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pma.dao.UserAccountRepository;
import com.company.pma.entities.UserAccount;

@Service
public class UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepo;
	
	public void save(UserAccount account) {
		userAccountRepo.save(account);
	}
}
