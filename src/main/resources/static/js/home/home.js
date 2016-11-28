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
			
			$http.post('/game/play', data, config)
		        .success(function (data, status, headers, config) {
		        	self.gameBoard = data;
		        	self.message='';
		        })
		        .error(function (data, status, header, config) {
		            self.message=data;
		        });		
		}
	}
		
	var getGameFnc = function() {
		if(self.gameBoard.currentPlayerId==-1) {
			/*var dataJ = $.param({
	            user: self.user
	        });*/
			
			var data = self.user;
	            
			$http.post('/game', data, config)
		        .success(function (data, status, headers, config) {
		        	self.gameBoard = data;
		        	self.message='';
		        })
		        .error(function (data, status, header, config) {
		            self.message=data;
		        });		
		}
	}
	
	
});
