package com.osetskiy.baby_shop.rest.utils;

import java.util.ArrayList;
import java.util.List;

import com.osetskiy.baby_shop.model.User;

public class AccountList {
	List<User> accounts = new ArrayList<>();
	public AccountList(List<User> accounts) {		
		this.accounts = accounts;
	}

	public List<User> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<User> accounts) {
		this.accounts = accounts;
	}
}
