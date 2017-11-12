package gravytrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gravytrain.repository.UserRepository;
import gravytrain.repository.user.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User getUser(int id) {
		return userRepository.findOne(id);
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return this.addUser(user);
	}

	public void deleteUser(int id) {
		userRepository.delete(id);

	}

	public List<User> getUsersBySurname(String surname) {
		return userRepository.findBySurname(surname);
	}
}
