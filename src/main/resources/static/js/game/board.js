angular.module("game", []).controller("board", function($scope, $http) {
	var self = this;
	var config = {
        headers : {
            'Content-Type': 'application/json; charset=utf-8;'
        }
    };
	
	self.board = {currentPlayerId: -1};
	
	var checkIfGameEnded = function() {
		if(self.board.gameEnded) {
			self.board.currentPlayerId=-1;
			if(self.board.kalahMap['1'].numOfStones > self.board.kalahMap['2'].numOfStones) {
				self.message="PLAYER 1 WON! GAME ENDED";
			} else {
				self.message="PLAYER 2 WON! GAME ENDED";
		    }
		}
	}

	var getCurrentBoard = function() {
        if(self.board.currentPlayerId===-1) {
            var data = self.boardId;
            $http.get("/api/currentBoard", data, config)
                .then(function successCallback(response) {
                    self.board = response.data;
                    self.message=" ";
                    checkIfGameEnded();
                }, function errorCallback(response) {
                    self.message=response.data.message;
                });
        }
    }

	self.play = function(playerId, pitId) {
		console.log(playerId + ' ' + pitId);
		if(self.board.currentPlayerId!==-1) {
			var data = {
	            boardId: self.boardId,
	            playerId: playerId,
	            pitId: pitId
	        };
			
			$http.put("/api/play", data, config)
                .then(function successCallback(response) {
                    self.board = response.data;
                    self.message='';
                    checkIfGameEnded();
                }, function errorCallback(response) {
                    self.message=response.data.message;
                });
		}
	}
		
	self.newBoard = function() {
		$http.post("/api/newBoard", config)
			.then(function successCallback(response) {
				self.board = response.data;
				self.boardId = response.data.boardId;
	        	self.message='';
			}, function errorCallback(response) {
				self.message=response.data.message;
			});
	}

});
