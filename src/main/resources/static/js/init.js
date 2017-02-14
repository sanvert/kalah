angular
		.module("kalah", [ "ngRoute", "game" ])
		.config(

				function($routeProvider, $httpProvider, $locationProvider) {

					$locationProvider.html5Mode(true);

					$routeProvider.when("/", {
						templateUrl : "js/game/board.html",
						controller : "board",
						controllerAs : "controller"
					}).otherwise("/");

					$httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";

        }).run();
