package com.soloxis.todo.controllers;


import java.util.Calendar;
import java.util.Date;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.soloxis.todo.models.Todo;

import com.soloxis.todo.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	// create method
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> createTodo(@RequestBody Todo todo, UriComponentsBuilder ucBuilder){
		System.out.println("Creating new todo");
		
		Calendar cal = Calendar.getInstance();
		todo.setDate(new Date(cal.getTime().getTime()));
		todoService.save(todo);
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(todo.getId()).toUri());
		
		LOGGER.debug("Todo was created");
		
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	}
	
	//read method
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> readTodo(){
		List<Todo> todos = todoService.findAll();
		if(todos == null){
			System.out.println("No todos were found");
			return new ResponseEntity<List<Todo>>(HttpStatus.NO_CONTENT);
		}
		LOGGER.debug("Todos were fetched");
		
		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}
	
	
	//update method
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo todo){
		Todo currentTodo = todoService.findById(id);
		
		if(currentTodo == null){
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		LOGGER.debug("Todo updated successfully");
		todoService.update(id, todo.isDone());
		
		return new ResponseEntity<Todo>(HttpStatus.OK);
	}
	
	//delete single todo method 
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Todo> deleteTodo(@PathVariable ("id") long id){
		Todo todo = todoService.findById(id);
		
		if(todo == null){
			return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
		}
		todoService.deleteById(id);
		
		return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
	}
	
	//delete all todos
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Todo> deleteAll(){
		todoService.deleteAll();
		
		return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
	}

	
}
