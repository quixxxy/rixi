var ctrl = angular.module('rixi-controller', [])

ctrl.controller('UserCtrl', function ($scope, User, $rootScope) {
    $scope.users = User.query();

    $scope.create = function (user) {
        user = User.save(user);

        $scope.users.push({'id': user.id, 'firstName': user.firstName, 'lastName': user.lastName});
        $scope.userCreateForm.$setPristine();
        $scope.user = {};
    }

    $scope.update = function (user, id) {
        User.update({id: id}, user);
    }

    $scope.delete = function (id, index) {
        User.delete({id: id});

        $scope.users.splice(index, 1);
    }
});

ctrl.controller("AlertCtrl", function($rootScope) {
    $rootScope.alerts = [];
});

ctrl.controller("LineCtrl", function ($scope, $http, $route) {
    $scope.activeTab = $route.current.$$route.activeTab;

    $http.get('rixi-rest/statistic/' + $scope.activeTab).
        success(function (data) {
            $scope.labels = data.labels;
            $scope.series = data.series;
            $scope.data = data.data;
        });

});

