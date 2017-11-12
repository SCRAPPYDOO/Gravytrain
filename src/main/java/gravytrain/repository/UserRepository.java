package gravytrain.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.CrudRepository;

import gravytrain.repository.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public default List<User> findBySurname(String surname) {
		String surnameTrimed = surname.trim().toLowerCase();
		if(surnameTrimed.equals("")) {
			return (List<User>)this.findAll();
		}
		return ((List<User>) this.findAll()).stream().filter(user ->
			user.getSurname().toLowerCase().contains(surnameTrimed)
		).collect(Collectors.toList());    
	}
}