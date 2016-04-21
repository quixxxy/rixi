var ctrl = angular.module('rixi-controller', [])

ctrl.controller('UserCtrl', function ($scope, User) {
    $scope.users = User.query();

    $scope.create = function (user) {
        User.save(user);

        $scope.users.push({'firstName': user.firstName, 'lastName': user.lastName});
    }

    $scope.update = function (user, id) {
        console.log(user);
        console.log(id);
        User.update({id: id}, user);
    }

    $scope.delete = function (id, index) {
        User.delete({id: id});
        $scope.users.splice(index, 1);
    }
});

ctrl.controller("LineCtrl", function ($scope, $http) {

    $http.get('rixi-rest/statistic/user').
        success(function(data) {
            $scope.labels = data.labels;
            $scope.series = data.series;
            $scope.data = data.data;
        });

});

