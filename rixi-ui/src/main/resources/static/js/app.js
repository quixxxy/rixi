app = angular.module('rixi-app', ['ngRoute', 'ngResource', 'rixi-controller', 'rixi-service']);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/',
            {
                controller: 'UserListCtrl',
                templateUrl: 'view/users.html'
            })
            .when('/user/:id',
            {
                controller: 'UserCtrl',
                templateUrl: 'view/user.html'
            })
            .when('/contact',
            {
                templateUrl: 'view/contact.html'
            })
            .when('/about',
            {
                templateUrl: 'view/about.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

