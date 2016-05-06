package com.osetskiy.baby_shop.REPO;

import org.springframework.data.repository.CrudRepository;

import com.osetskiy.baby_shop.model.User;

public interface AccountRepository extends CrudRepository<User, String>{
	User findByName(String name);
}
