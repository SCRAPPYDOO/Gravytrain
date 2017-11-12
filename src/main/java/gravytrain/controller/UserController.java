package gravytrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gravytrain.repository.user.User;
import gravytrain.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/userBySurname/{surname}")
	public List<User> getUsersBySurname(@PathVariable String surname) {
		return userService.getUsersBySurname(surname);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public void deleteUser(@PathVariable int id) {
	  userService.deleteUser(id);
	}
}
