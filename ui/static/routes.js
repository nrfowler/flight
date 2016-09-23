/* @ngInject */
angular.
  module('flight').
  config(['$routeProvider',
    function config($routeProvider) {
        console.log("hello console")
      $routeProvider.
      	when('/flight', {

          template: '<flight-app></flight-app>'

        }).
      	when('/user', {

          template: '<p>hi</hi>'

        }).
        when('/user/:id', {

        	template: '<user-app></user-app>'
        }).
        when('/login', {

        	template: '<login-app></login-app>'
        }).when('/home', {

        	template: '<home-app></home-app>'
        }).when('/home', {

        	template: '<home-app></home-app>'
        }).
        when('/register', {

        	template: '<register-app></register-app>'
        }).
        when('/createtweet', {

        	template: '<create-app></create-app>'
        }).
        otherwise('/users');
    }
  ]);
