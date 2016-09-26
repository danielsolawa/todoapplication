var application = angular.module('todoApp', []);
application.controller('TodoController', ['$http', function($http){
	var todo = this;
	todo.tasks = [];
	todo.text = 'What need to be done?';
	
	var getData = 
		$http.get('/api').success(function(data){
			todo.tasks = data;
		});
	
	
	todo.updateDone = function(updatedTodo){
		$http.put('/api/' + updatedTodo.id, updatedTodo).success(function(data){
			
		});
	};
	
	todo.remaining = function(){
		var count = 0;
		angular.forEach(todo.tasks, function(todo){
			count += todo.done ? 0 : 1;
		});
		return count;
	};
	
	todo.deleteById = function(todoToDelete){
		$http.delete('/api/' + todoToDelete.id, todoToDelete).success(function(data){
			$http.get('/api').success(function(data){
				todo.tasks = data;
			});
		});
	};
	
	todo.deleteAll = function(){
		$http.delete('/api').success(function(data){
			$http.get('/api').success(function(data){
				todo.tasks = data;
			});
		});
	};
	

	
	todo.addTodo = function(){
		
		todo.date = Date.now();
		var sendTodo = {'text': todo.text, 'done': false};
		
	
		$http.post('/api', sendTodo).success(function(data){
			todo.text = '';
			$http.get('/api').success(function(data){
				todo.tasks = data;
			});
		});
	};
}]);