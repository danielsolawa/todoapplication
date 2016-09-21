package com.soloxis.todo.service;

import java.util.List;

import com.soloxis.todo.models.Todo;

public interface TodoService {
	
	Todo save(Todo todo);
	List<Todo> findAll();
	void update(long id, boolean done);

}
