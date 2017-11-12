package gravytrain.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gravytrain.GravytrainTest;
import gravytrain.repository.user.User;
import gravytrain.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest extends GravytrainTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		List<User> users = getTempUsers();

		Mockito.when(userService.getUsers()).thenReturn(users);
		Mockito.when(userService.getUser(1)).thenReturn(users.get(0));
		Mockito.when(userService.getUsersBySurname("Surname2")).thenReturn(users);
	}

	@Test
	public void getUsersTest() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Name1")));
	}

	@Test
	public void getUserTest() throws Exception {
		this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Title1")));
	}

	@Test
	public void addUserTest() throws Exception {
		String userJson = mapper.writeValueAsString(getTempUser());
		this.mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void updateUserTest() throws Exception {
		String userJson = mapper.writeValueAsString(getTempUser());
		this.mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void deleteUserTest() throws Exception {
		String userJson = mapper.writeValueAsString(getTempUser());
		this.mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
	

	@Test
	public void getUsersBySurname() throws Exception {
		this.mockMvc.perform(get("/userBySurname/")).andDo(print())
				.andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/userBySurname/aaaa")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
}
