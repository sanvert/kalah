angular.module('home', []).controller('home', function($scope, $http) {
	var self = this;
	$http.get('/user/').then(function(response) {
		self.user = response.data.name;
		getGameFnc();
	});
	
	var config = {
        headers : {
            'Content-Type': 'application/json; charset=utf-8;'
        }
    };
	
	self.gameBoard = {currentPlayerId: -1};
	
	self.playFnc = function(playerId, pitId) {
		console.log(playerId + ' ' + pitId);
		if(self.gameBoard.currentPlayerId!=-1) {
			var data = {
	            user: self.user,
	            playerId: playerId,
	            pitId: pitId
	        };
			
			$http.post('/currentGame/play', data, config)
				.then(function successCallback(response) {
					self.gameBoard = response.data;
		        	self.message='';
				  }, function errorCallback(response) {
					self.message=response.data.message;
				  });	
		}
	}
		
	self.newGame = function() {
		var data = self.user;      
		$http.post('/newGame', data, config)
			.then(function successCallback(response) {
				self.gameBoard = response.data;
	        	self.message='';
			  }, function errorCallback(response) {
				self.message=response.data.message;
			  });	
	}
	
	var getGameFnc = function() {
		if(self.gameBoard.currentPlayerId==-1) {			
			var data = self.user;	            
			$http.post('/currentGame', data, config)
				.then(function successCallback(response) {
					self.gameBoard = response.data;
		        	self.message='';
				  }, function errorCallback(response) {
					self.message=response.data.message;
				  });	
		}
	}
	
	
});
