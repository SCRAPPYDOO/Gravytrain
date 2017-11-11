package gravytrain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gravytrain.repository.user.User;

public abstract class GravytrainTest {
	public User getTempUser() {
		return new User("Title1", "Martin", "Makowski", new Date(), true);
	}
	
	public List<User> getTempUsers() {
		List<User> temp = new ArrayList<User>();
		temp.add(new User(1, "Title1", "Name1", "Surname1", new Date(), true));
		temp.add(new User(2, "Title2", "Name2", "Surname2", new Date(), true));
		temp.add(new User(3, "Title3", "Name3", "Surname3", new Date(), false));
		return temp;
	}
}
