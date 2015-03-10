package org.holbreich.web.ums;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/users")
public class UserController {



	    @RequestMapping(value="/{user}", method=RequestMethod.GET)
	    public User getUser(@PathVariable long user) {
	       return new User(user);
	    }

//	    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
//	    List<Customer> getUserCustomers(@PathVariable long user) {
//	        // ...
//	    }

	    @RequestMapping(value="/{user}", method=RequestMethod.DELETE)
	    public User deleteUser(@PathVariable Long user) {
	    	return new User(user);
	    }

}
