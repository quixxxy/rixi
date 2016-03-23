app = angular.module('rixi', []);

app.controller('UserCtrl', function ($scope, $http) {
    $http.get("http://localhost:8081/user")
        .success(function (response) {
            $scope.users = response;
        });
});
