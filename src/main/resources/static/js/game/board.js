angular.module("game", []).controller("board", function($scope, $http) {
	var config = {
        headers : {
            'Content-Type': 'application/json; charset=utf-8;'
        }
    };
	
	$scope.board = {currentPlayerId: -1};
	
	var checkIfGameEnded = function() {
		if($scope.board.gameEnded) {
			$scope.board.currentPlayerId=-1;
			if($scope.board.kalahMap['1'].numOfStones > $scope.board.kalahMap['2'].numOfStones) {
				$scope.message="PLAYER 1 WON! GAME ENDED";
			} else {
				$scope.message="PLAYER 2 WON! GAME ENDED";
		    }
		}
	}

	$scope.getCurrentBoard = function() {
        if(self.board.currentPlayerId===-1) {
            $http.get("/api/currentBoard/" + $scope.boardId, config)
                .then(function successCallback(response) {
                    $scope.board = response.data;
                    $scope.message=" ";
                    checkIfGameEnded();
                }, function errorCallback(response) {
                    $scope.message=response.data.message;
                });
        }
    }

	$scope.play = function(playerId, pitId) {
		console.log($scope.boardId);
		if($scope.board.currentPlayerId!==-1) {
			var data = {
	            boardId: $scope.boardId,
	            playerId: playerId,
	            pitId: pitId
	        };
			
			$http.put("/api/play", data, config)
                .then(function successCallback(response) {
                    $scope.board = response.data;
                    $scope.message='';
                    checkIfGameEnded();
                }, function errorCallback(response) {
                    $scope.message=response.data.message;
                });
		}
	}
		
	$scope.newBoard = function() {
		$http.post("/api/newBoard", config)
			.then(function successCallback(response) {
				$scope.board = response.data;
				$scope.boardId = response.data.boardId;
	        	$scope.message='';
			}, function errorCallback(response) {
				$scope.message=response.data.message;
			});
	}

});
