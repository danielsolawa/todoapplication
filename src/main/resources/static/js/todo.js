var application = angular.module('todoApp', []);
application.controller('TodoController', ['$http', function($http){
	var todo = this;
	todo.tasks = [];
	todo.check = false;
	
	$http.get('/api/getall').success(function(data){
		todo.tasks = data;
	});
	
	todo.updateDone = function(updatedTodo){
		$http.post('/api/update', {done : updatedTodo.done, id : updatedTodo.id}).success(function(data){
			
		});
	};
	
	todo.addTodo = function(){
		todo.date = Date.now();
		$http.post('/api/save', {text: todo.text, done : false}).success(function(data){
			$http.get('/api/getall').success(function(data){
				todo.tasks = data;
			});
		});
	};
}]);