var ctrl = angular.module('rixi-controller', [])

ctrl.controller('UserCtrl', function ($scope, $routeParams, $window, User) {
    $scope.user = User.get({id: $routeParams.id});

    $scope.update = function (user) {
        User.update({id: user.id}, user);
    }
});

ctrl.controller('UserListCtrl', function ($scope, User) {
    $scope.users = User.query();

    $scope.create = function (user) {
        User.save(user);
        $scope.users.push(user);
    }

    $scope.delete = function (id, index) {
        User.delete({id: id});
        $scope.users.splice(index, 1);
    }
});
