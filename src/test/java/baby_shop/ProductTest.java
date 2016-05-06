package baby_shop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.osetskiy.baby_shop.rest.mvc.ProductControllerRest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.glassfish.jersey.message.internal.MediaTypes;

public class ProductTest {
	@InjectMocks
	private ProductControllerRest controller_REST;
	private MockMvc mockMVC;
	@Before
	public void Init(){
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(controller_REST).build();	
		
	}
//	@Test
	public void test() throws Exception {
		mockMVC.perform(get("/api/products/test2").accept(MediaType.APPLICATION_JSON)).andDo(print());
		
	}
	
	//@Test
	public void test2() throws Exception {
		mockMVC.perform(post("/api/products/test3").accept(MediaType.APPLICATION_JSON).content("{\"id\": \"118A015\",\"name\": \"Chambray Romper(CHAMBRAY)(118A015)\",  \"price\": 301,\"featured\": null,\"category\": {\"id\": \"000000001\",\"name\": \"Carters_новые\"}}")).andDo(print());
		
	}	
	

}
