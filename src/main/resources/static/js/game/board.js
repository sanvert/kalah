angular.module('game', []).controller('board', function($scope, $http) {
	var self = this;
//	$http.get('/user/').then(function(response) {
//		self.user = response.data.name;
//		getGameFnc();
//	});
	
	var config = {
        headers : {
            'Content-Type': 'application/json; charset=utf-8;'
        }
    };
	
	self.board = {currentPlayerId: -1};
	
	var checkIfGameEnded = function() {
		if(self.gameBoard.gameEnded) {
			self.gameBoard.currentPlayerId=-1;
			if(self.gameBoard.kalahMap['1'].numOfStones > self.gameBoard.kalahMap['2'].numOfStones)
				self.message='PLAYER 1 WON';
			else
				self.message='PLAYER 2 WON';
		}
	}
	
	self.play = function(playerId, pitId) {
		console.log(playerId + ' ' + pitId);
		if(self.gameBoard.currentPlayerId!==-1) {
			var data = {
	            user: self.user,
	            playerId: playerId,
	            pitId: pitId
	        };
			
			$http.post('/api/play', data, config)
				.then(function successCallback(response) {
					self.gameBoard = response.data;
		        	self.message='';
					checkIfGameEnded();
				  }, function errorCallback(response) {
					self.message=response.data.message;
				  });	
		}
	}
		
	self.newBoard = function() {
		var data = self.user;      
		$http.post('/api/newBoard', data, config)
			.then(function successCallback(response) {
				self.board = response.data;
	        	self.message='';
			  }, function errorCallback(response) {
				self.message=response.data.message;
			  });	
	}
	
	var getCurrentBoard = function() {
		if(self.gameBoard.currentPlayerId===-1) {
			var data = self.user;	            
			$http.post('/api/currentBoard', data, config)
				.then(function successCallback(response) {
					self.gameBoard = response.data;
		        	self.message=' ';
					checkIfGameEnded();
				  }, function errorCallback(response) {
					self.message=response.data.message;
				  });	
		}
	}
	
	
});
