var ctrl = angular.module('rixi-controller', [])

ctrl.controller('UserCtrl', function ($scope, $routeParams, User) {
    $scope.user = User.get({id: $routeParams.id});

    $id = $scope.user.id;

    $scope.update = function () {
        User.update({id: $id}, user);
    }
});

ctrl.controller('UserListCtrl', function ($scope, User) {
    $scope.users = User.query();
});
