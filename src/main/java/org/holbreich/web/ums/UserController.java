package org.holbreich.web.ums;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.holbreich.web.ums.db.UserServiceDAO;
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

	private static final Log LOG =  LogFactory.getLog(UserController.class);
	
	@Autowired
	private UserServiceDAO userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable long userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody User user) {
		long createNewUser = userService.createNewUser(user);
		// TODO check for dupplicates.
		user.setId(createNewUser);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
		
	}

	@RequestMapping(method=RequestMethod.POST, 
	        consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createRole(User user) {
	
	    long createNewUser = userService.createNewUser(user);
		// TODO check for dupplicates.
	    LOG.info("New user registered id "+ createNewUser);
		user.setId(createNewUser);
		return "redirect:/users";
	}

	private String getParam(Map<String, String[]> parameterMap, String key) {
	 String[] value = parameterMap.getOrDefault(key, null);
	 if(value!=null && value.length>0){
		 return value[0]; 
	 }
	return null;
}

	


}
