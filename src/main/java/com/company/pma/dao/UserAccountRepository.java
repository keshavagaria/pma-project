package com.company.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

}
