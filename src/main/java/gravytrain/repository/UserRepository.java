package gravytrain.repository;

import org.springframework.data.repository.CrudRepository;

import gravytrain.exceptions.ActiveUserException;
import gravytrain.repository.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public default void deleteUser(User user) throws ActiveUserException {
		try {
			this.delete(user);
		} catch(Exception e) {
			throw new ActiveUserException();
		}
	}
}
