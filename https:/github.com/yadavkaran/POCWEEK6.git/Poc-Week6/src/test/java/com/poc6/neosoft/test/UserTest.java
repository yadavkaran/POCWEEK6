package com.poc6.neosoft.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.any;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import com.poc6.neosoft.model.Response;
import com.poc6.neosoft.model.User;
import com.poc6.neosoft.controller.UserController;
import com.poc6.neosoft.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setFirstName("Nupur");
		user.setLastName("Deshmukh");
		user.setContact("9753417839");
		user.setEmail("nupur.deshmukh@gmail.com");
		user.setCity("Mumbai");
		user.setCountry("India");
	

		String JsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc
				.perform(post("/saveUser").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContext, Response.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getAllUsers() throws Exception {

		MvcResult result = mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());

	}

	@Test
	public void getUserById() throws Exception {
		MvcResult result = mockMvc.perform(get("/User/search/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getUserByName() throws Exception {
		MvcResult result = mockMvc.perform(get("/User/search/Dipti").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getUserByPinCode() throws Exception {
		MvcResult result = mockMvc.perform(get("/User/search/400091").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void updateUserByEmployeeId() throws Exception {
		User update = new User();
		update.setFirstName("Nupur");
		update.setLastName("Sharma");
		update.setContact("7652810271");
		update.setEmail("nupur.deshmukh@gmail.com");
		update.setCity("Nallasopara");
		update.setCountry("India");
		String JsonRequest = objectMapper.writeValueAsString(update);
		MvcResult result = mockMvc
				.perform(put("/updateUser/10").content(JsonRequest)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void deleteUserById() throws Exception {
		MvcResult result = mockMvc
				.perform(delete("/deleteUser/12").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContext, Response.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

}
