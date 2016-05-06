package com.osetskiy.baby_shop.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.osetskiy.baby_shop.rest.mvc.AccountControllerRest;
import com.osetskiy.baby_shop.rest.resources.AccountListResource;
import com.osetskiy.baby_shop.rest.resources.AccountResource;
import com.osetskiy.baby_shop.rest.utils.AccountList;



import java.util.List;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

	

	public AccountListResourceAsm(Class<AccountControllerRest> controllerClass, Class<AccountListResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public AccountListResource toResource(AccountList list) {		
		List<AccountResource> resTmp =  new AccountResourceAsm(AccountControllerRest.class,AccountResource.class).toResources(list.getAccounts());;
		AccountListResource res = new AccountListResource();
		res.setAccounts(resTmp);
		return res;		
		
	}

}
