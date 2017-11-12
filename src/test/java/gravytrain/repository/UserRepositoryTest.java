package gravytrain.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import gravytrain.GravytrainTest;
import gravytrain.repository.user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest extends GravytrainTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void getUsers() {
		userRepository.save(getTempUser());
		userRepository.save(getTempUser());
		assertEquals(2, ((List<User>)userRepository.findAll()).size());
	}

	@Test
	public void getUser() {
		User user = this.getTempUser();
		assertNotEquals(user, userRepository.findOne(user.getId()));
		User savedUser = userRepository.save(user);
		assertEquals(savedUser, userRepository.findOne(savedUser.getId()));
	}

	@Test
	public void addUser() {
		User user = this.getTempUser();
		user.setId(1);
		assertNotEquals(user, userRepository.findOne(user.getId()));
		User savedUser = userRepository.save(user);
		User userFromDb = userRepository.findOne(savedUser.getId());
		assertEquals(savedUser, userFromDb);
	}

	@Test
	public void updateUser() {
		User user = this.getTempUser();
		String string = user.getFirstname() + " " + user.getSurname();
		User savedUser = userRepository.save(user);
		assertEquals(string, savedUser.getFirstname() + " " + savedUser.getSurname());
		savedUser.setFirstname("Bill");
		savedUser.setSurname("Gates");
		savedUser = userRepository.save(savedUser);
		assertNotEquals(string, savedUser.getFirstname() + " " + savedUser.getSurname());
		assertEquals("Bill Gates", savedUser.getFirstname() + " " + savedUser.getSurname());	
	}

	@Test(expected = RuntimeException.class) 
	public void deleteActiveUser() {
		User savedUser = userRepository.save(getTempUser());
		userRepository.delete(savedUser);
	}
	
	@Test
	public void deleteUser() {
		User savedUser = getTempUser();
		savedUser.setActive(false);
		userRepository.save(savedUser);
		userRepository.delete(savedUser);
		assertEquals(null, userRepository.findOne(savedUser.getId()));
	}
	
	@Test 
	public void findBySurname() {
		User savedUser = getTempUser();
		savedUser.setSurname("aaaa");
		userRepository.save(savedUser);
		savedUser = getTempUser();
		savedUser.setSurname("aaa");
		userRepository.save(savedUser);
		savedUser = getTempUser();
		savedUser.setSurname("bbb");
		userRepository.save(savedUser);
		assertEquals(3, ((List<User>)userRepository.findAll()).size());
		assertEquals(2, userRepository.findBySurname("aaa").size());
		assertEquals("aaaa", userRepository.findBySurname("aaaa").get(0).getSurname());
		assertEquals(3, userRepository.findBySurname("").size());
	}

}
