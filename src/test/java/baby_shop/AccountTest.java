package baby_shop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.osetskiy.baby_shop.REPO.AccountRepository;
import com.osetskiy.baby_shop.model.Role;
import com.osetskiy.baby_shop.model.User;
import com.osetskiy.baby_shop.rest.mvc.AccountControllerRest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;



public class AccountTest {
	@InjectMocks
	private AccountControllerRest accountController;
	
	@Mock
	private AccountRepository accountRepository;
	
	private MockMvc mockMVC;
	@Before
	public void Init(){
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(accountController).build();	
		
	}	
	@Test
	public void test2() throws Exception {
		User us = new User();
		us.setName("dklsjfkljsd");
		us.setPhone("2343");
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("ROLE_ADMIN"));
		roles.add(new Role("ROLE_AAAA"));
		us.setRoles(roles);
		System.out.println("ROLSE ARE "+ us.getRolesAsStrings());
		
		List<User> listUsers = new ArrayList<User>();
		listUsers.add(us);
		
		
		/*when(accountRepository.findAll()).thenReturn(listUsers);
		mockMVC.perform(get("/rest/accounts").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());*/
		
	}	
	

}
