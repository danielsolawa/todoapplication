package com.soloxis.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.soloxis.todo.models.Todo;

public interface TodoRepository extends Repository<Todo, Long> {
	
	List<Todo> findAll();
	Todo save(Todo todo);
	
	
	@Modifying
	@Query("UPDATE Todo t SET t.done = :done WHERE t.id = :id")
	void update(@Param("id") long id, @Param("done") boolean done);

}
