package org.holbreich.web.ums;

import org.holbreich.web.ums.service.UserServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserServiceDAO userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable long userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody User user) {
		long createNewUser = userService.createNewUser(user);
		// TODO check for dupplicates.
		user.setId(createNewUser);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
		
	}

//	@RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
//	public User deleteUser(@PathVariable Long user) {
//		return new User(user);
//	}

}
