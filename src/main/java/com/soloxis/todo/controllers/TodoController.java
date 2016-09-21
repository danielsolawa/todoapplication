package com.soloxis.todo.controllers;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soloxis.todo.models.Todo;

import com.soloxis.todo.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> saveTodo(@RequestBody Map<String, Object> data){
		String text = (String) data.get("text");
		Calendar cal = Calendar.getInstance();
		Date date = new Date(cal.getTime().getTime());
		boolean done =  (boolean) data.get("done");
		
		Todo todo = new Todo(text, done, date);
		todoService.save(todo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		
		
		return result;
	}
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<Todo> getAll(){
		
		List<Todo> allTodos = todoService.findAll();
		
		
		return allTodos;
	}
	
	@RequestMapping(value= "/update", method = RequestMethod.POST)
	public Map<String, Object> updateTodo(@RequestBody Map<String, Object> data){
		boolean done = (boolean) data.get("done");
		int  idInt = (int) data.get("id");
		Long id = new Long(idInt);
		
		todoService.update(id, done);
		
		
		Map<String, Object> result= new HashMap<>();
		result.put("success", true);
		
		
		return result;
	}
}
