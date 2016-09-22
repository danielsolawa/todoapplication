package com.soloxis.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soloxis.todo.models.Todo;
import com.soloxis.todo.repositories.TodoRepository;


@Service
@Transactional
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Todo save(Todo todo) {
		
		return todoRepository.save(todo);
	}

	@Override
	public List<Todo> findAll() {
		
		return todoRepository.findAll();
	}

	@Override
	public void update(long id, boolean done) {
		todoRepository.update(id, done);
		
	}

	@Override
	public Todo findById(long id) {
		
		return todoRepository.findById(id);
	}

	@Override
	public Long deleteById(long id) {
		
		return todoRepository.deleteById(id);
	}

	@Override
	public List<Todo> deleteAll() {
		
		return todoRepository.deleteAll();
	}

}
