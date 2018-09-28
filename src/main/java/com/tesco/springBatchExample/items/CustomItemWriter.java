package com.tesco.springBatchExample.items;

import java.util.List;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.tesco.springBatchExample.model.User;
import com.tesco.springBatchExample.repo.UserRepository;

public class CustomItemWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends User> items) throws Exception {
		
		for (User user : items) {
			user.setId(String.valueOf((int)Math.random()*100));
			userRepository.save(user);
		}
			
	}
}
