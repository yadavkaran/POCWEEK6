package com.neosoft.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc6.neosoft.model.User;
import com.poc6.neosoft.model.Response;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserTest {
private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	  @Test public void creatUser() throws Exception { 
	  User user=new User();
	  user.setFirstName("Amit"); 
	  user.setLastName("Mishra"); 
	  user.setContact("91234567098");
	  user.setEmail("amit2108@gmail.com");
	  user.setCity("Bhayandar");
	  user.setCountry("India");
	  String JsonRequest= objectMapper.writeValueAsString(user);
	  MvcResult result=mockMvc.perform(post("/users1/createUser").content(JsonRequest).contentType(
	  MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).
	  andReturn();
	  String resultContext =result.getResponse().getContentAsString(); 
	  Response response=objectMapper.readValue(resultContext, Response.class);
	  Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
	  Assertions.assertEquals("Success",response.getProgressMessage()); }
	 
	 
	@Test
	public void getAllUsers() throws Exception {
		
		MvcResult result = mockMvc.perform(get("/users1/getAllUser").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		String resultContext = result.getResponse().getContentAsString();
		
		Response response = objectMapper.readValue(resultContext, Response.class);
		
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
			
		}	
	
	@Test
	public void getUserByName() throws Exception {	
		MvcResult result = mockMvc.perform(get("/users1/name").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		String resultContext = result.getResponse().getContentAsString();
		
		Response response = objectMapper.readValue(resultContext, Response.class);
		
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());	
		}

	@Test
	public void getUserById() throws Exception {	
		MvcResult result = mockMvc.perform(get("/users1/id/6").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		String resultContext = result.getResponse().getContentAsString();
		
		Response response = objectMapper.readValue(resultContext, Response.class);
		
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());	
		}	
	
	@Test
	public void getUserByEmployeeId() throws Exception {	
		MvcResult result = mockMvc.perform(get("/users1/empid").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		String resultContext = result.getResponse().getContentAsString();
		
		Response response = objectMapper.readValue(resultContext, Response.class);
		
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());	
		}	
	@Test 
	public void getUserByCity() throws Exception{
		MvcResult result = mockMvc.perform(get("/users1/city/").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		
		String resultContext = result.getResponse().getContentAsString();
		
		Response response = objectMapper.readValue(resultContext, Response.class);
		
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success",response.getProgressMessage());
			
	}
	
	
	  @Test 
	public void deleteUserById() throws Exception{ 
	  MvcResult result =  mockMvc.perform(delete("/users1/deleteUser/15").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).
	  andExpect(status().isOk()).andReturn(); 
	  String resultContext=result.getResponse().getContentAsString(); 
	  Response response=objectMapper.readValue(resultContext, Response.class);
	  Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
	  Assertions.assertEquals("Success",response.getProgressMessage()); 
	  }
	  
	  
	  }
	 