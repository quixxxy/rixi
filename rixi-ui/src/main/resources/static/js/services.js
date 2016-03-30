var service = angular.module('rixi-service', []);

service.factory('User', function ($resource) {
    return $resource('http://localhost:8081/users/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });
});