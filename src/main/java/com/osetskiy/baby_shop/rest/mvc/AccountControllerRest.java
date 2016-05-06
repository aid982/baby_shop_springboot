package com.osetskiy.baby_shop.rest.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osetskiy.baby_shop.REPO.AccountRepository;
import com.osetskiy.baby_shop.REPO.RoleRepository;
import com.osetskiy.baby_shop.model.Role;
import com.osetskiy.baby_shop.model.User;
import com.osetskiy.baby_shop.rest.resources.AccountListResource;
import com.osetskiy.baby_shop.rest.resources.AccountResource;
import com.osetskiy.baby_shop.rest.resources.asm.AccountListResourceAsm;
import com.osetskiy.baby_shop.rest.resources.asm.AccountResourceAsm;
import com.osetskiy.baby_shop.rest.utils.AccountList;
import com.osetskiy.baby_shop.rest.utils.exceptions.AccountExistException;
import com.osetskiy.baby_shop.rest.utils.exceptions.ForbiddenException;

@Controller
@RequestMapping("rest/accounts")
public class AccountControllerRest {
	private AccountRepository accountRepository;
	private RoleRepository roleRepository;
	
	

	@Autowired
	public AccountControllerRest(AccountRepository accountRepository, RoleRepository roleRepository) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
		if (accountRepository.findByName(sentAccount.getName())!=null) {
			throw new AccountExistException();			
		}
		Role currentRole;
		if(accountRepository.count()==0) {
			currentRole=roleRepository.findOne("ROLE_ADMIN");			 
		}
		else
		{
			currentRole=roleRepository.findOne("ROLE_USER");		
		}		
		User user = sentAccount.toUser();
		List<Role> rolesList = new ArrayList<>();
		rolesList.add(currentRole);
		user.setRoles(rolesList);
		accountRepository.save(user);
		AccountResource res = new AccountResourceAsm(AccountControllerRest.class, AccountResource.class)
				.toResource(user);
		return new ResponseEntity<AccountResource>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/{sentUserName}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(@PathVariable String sentUserName) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String active_username="";
		if (principal instanceof UserDetails) {		
			active_username = ((UserDetails) principal).getUsername();
		}			
		User active_user = accountRepository.findByName(active_username);
		// Check if user is Admin or have credentials to receive account information
		if((active_user==null)||(!active_username.equals(sentUserName)&&!(active_user.isAdmin())))
		{
			throw new ForbiddenException();		
		}
		User user = accountRepository.findByName(sentUserName);
		if (user != null) {
			AccountResource res = new AccountResourceAsm(AccountControllerRest.class, AccountResource.class)
					.toResource(user);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/exist/{sentUserName}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> accountExists(@PathVariable String sentUserName) {		
		User user = accountRepository.findByName(sentUserName);
		if (user != null) {			
			return new ResponseEntity<AccountResource>(HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccountListResource> getAccounts() {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username;
			username = ((UserDetails) principal).getUsername();
			User user = accountRepository.findByName(username);
			if (user != null) {
				List<User> listUsers = (List<User>) accountRepository.findAll();
				AccountList accountList = new AccountList(listUsers);
				AccountListResource res = new AccountListResourceAsm(AccountControllerRest.class,
				AccountListResource.class).toResource(accountList);
				return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
			}

		} else {
			throw new ForbiddenException();
		}
		return null;

	}
	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity<Void> handleForbidenException(Exception ex) {
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		
	}

}
