package com.soloxis.todo.repositories;


import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.soloxis.todo.models.Todo;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TodoRepositoryTests {
	
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Before
	public void init(){
		todoRepository.deleteAll();
	}
	
	@Test
	public void returnedListendShouldNotBeNull(){
		Calendar cal = Calendar.getInstance();
		todoRepository.save(new Todo("hello", true, new Date(cal.getTime().getTime())));
		List<Todo> todos = todoRepository.findAll();
		
		assertNotNull("List shouldn't be null", todos);
		
		
	}
	


}
