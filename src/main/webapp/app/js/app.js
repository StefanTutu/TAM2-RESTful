// WebClient URL is: 			http://localhost:8080/SCRUM/app/Start.html
// Rest Data Service URL is: 	http://localhost:8080/SCRUM/data/projects

var app = angular.module('App', ['ngGrid', 'ngRoute', 'ngResource']);
var softwaresRestURL = 'http://localhost:8080/TAM2/data/softwares';
var futureReleasesRestURL = 'http://localhost:8080/TAM2/data/futurereleases';
var updatesRestURL = 'http://localhost:8080/TAM2/data/patches';
var teamsRestURL = 'http://localhost:8080/TAM2/data/teams';

//App navigation control
app.config(
		function($routeProvider){
			$routeProvider
				.when('/', 		{controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view1', {controller:'view1Controller', templateUrl: 'partials/view1.html'})
				.when('/view2', {controller:'view2Controller', templateUrl: 'partials/view2.html'})
				.when('/view3', {controller:'view3Controller', templateUrl: 'partials/view3.html'})
				.when('/view4', {controller:'view4Controller', templateUrl: 'partials/view4.html'})
				.when('/view5', {controller:'view5Controller', templateUrl: 'partials/view5.html'})
				.when('/view6', {controller:'view6Controller', templateUrl: 'partials/view6.html'})
				.when('/view7', {controller:'view7Controller', templateUrl: 'partials/view7.html'})
				.when('/view8', {controller:'view8Controller', templateUrl: 'partials/view8.html'})
				.when('/view9', {controller:'view9Controller', templateUrl: 'partials/view9.html'})
				.when('/view10', {controller:'view10Controller', templateUrl: 'partials/view10.html'})
				.when('/view11', {controller:'view11Controller', templateUrl: 'partials/view11.html'})
				.when('/view12', {controller:'view12Controller', templateUrl: 'partials/view12.html'})
				.when('/view13', {controller:'view13Controller', templateUrl: 'partials/view13.html'})
				.otherwise({redirectTo: '/'});
		}
);


function reload() {

	window.location.reload();
}

function reloadPartial(){
	var cnt = document.getElementById("containerF");
    var cont = cnt.innerHTML;
    cnt.innerHTML= cont;
	
}
