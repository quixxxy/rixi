app = angular.module('rixi-app', ['ngRoute']);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/',
            {
                controller: 'UsersCtrl',
                templateUrl: 'view/users.html'
            })
            .when('/user/:userId',
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

app.controller('UsersCtrl', function ($scope, $http) {
    $http.get("http://localhost:8081/user")
        .success(function (response) {
            $scope.users = response;
        });
});

app.controller('UserCtrl', function ($scope, $http, $routeParams) {
    $http.get("http://localhost:8081/user/" + $routeParams.userId)
        .success(function (response) {
            $scope.user = response;
        });

    $scope.update = function () {
        console.log("123");
    };
});
