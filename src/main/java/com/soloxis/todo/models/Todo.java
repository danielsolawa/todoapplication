package com.soloxis.todo.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String text;
	private boolean done;
	private Date date;
	
	public Todo() {
		
	}
	

	
	public Todo(String text, boolean done, Date date) {
		super();
		this.text = text;
		this.done = done;
		this.date = date;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", text=" + text + ", done=" + done + ", date=" + date + "]";
	}
	
	
	

}
