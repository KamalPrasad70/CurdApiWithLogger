package com.userentity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userentity.entity.User;
import com.userentity.repository.UserRepository;
@RestController
@RequestMapping("/apis")
public class UserController {

	private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping(value="/")
	public String getPage() {
		return "hello";
	}
	
	@GetMapping(value="/users")
	public List <User> getUssers(){
		LOGGER.info("going to findAll()");
		return userRepo.findAll();
		
	}
	@PostMapping(value="/save")
	public String saveUser(@RequestBody User user) {
		LOGGER.info("going to save()");
		userRepo.save(user);
		return "saved user";
	}	
	
	@PutMapping(value="/update/{id}")
	public String updateUser(@PathVariable long id, @RequestBody User user) {
		LOGGER.info("going to updateThe user");
		User updatedUser =userRepo.findById(id).get();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setAge(user.getAge());
		userRepo.save(updatedUser);
		return "Updated...";
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		LOGGER.info("going to delete the user");
		User deleteUser =userRepo.findById(id).get();
		userRepo.delete(deleteUser);
		return "Delete user with the id: " +id;
		
	}
	


}
