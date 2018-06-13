package com.tesco.springBatchExample.items;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.tesco.springBatchExample.model.User;

public class CustomItemProcessor implements ItemProcessor<User,User> {

	@Override
	public User process(User user) throws Exception {
		
		Timestamp createdDate = new Timestamp(new Date().getTime());
		user.setCreatedDate(createdDate);
		return user;
	}

}
