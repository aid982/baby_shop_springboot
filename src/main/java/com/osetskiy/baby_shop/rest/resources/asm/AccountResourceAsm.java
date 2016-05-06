package com.osetskiy.baby_shop.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.osetskiy.baby_shop.model.User;
import com.osetskiy.baby_shop.rest.mvc.AccountControllerRest;
import com.osetskiy.baby_shop.rest.resources.AccountResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AccountResourceAsm extends ResourceAssemblerSupport<User, AccountResource> {

	

	public AccountResourceAsm(Class<AccountControllerRest> controllerClass, Class<AccountResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public AccountResource toResource(User user) {
		
		
		AccountResource res = createResourceWithId(user.getId(), user);
		res.setIsAdmin(user.hasRole("ROLE_ADMIN"));
		res.setName(user.getName());
		res.setPassword(user.getPassword());
		res.setEmail(user.getEmail());
		res.setFirstName(user.getFirstName());
		res.setPhone(user.getPhone());
		Link link = linkTo(AccountControllerRest.class).slash(user.getName()).withSelfRel();		
		res.add(link);
		
			return res;
	}

}
