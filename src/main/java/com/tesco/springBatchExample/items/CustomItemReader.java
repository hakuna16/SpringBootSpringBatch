package com.tesco.springBatchExample.items;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.tesco.springBatchExample.model.User;

public class CustomItemReader implements ItemReader<User> {

	private static List<User> users = new ArrayList<>(2);
	private static int index = 0;
	static {
		users.add(new User("CustomReader" + String.valueOf((int)Math.random()*100)));
		users.add(new User("CustomReader" + String.valueOf((int)Math.random()*100)));
	}
	
	@Override
	public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index < users.size()) {
			return users.get(index++);
		}
		return null;
	}

}
