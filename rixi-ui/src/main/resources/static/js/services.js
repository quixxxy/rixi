var service = angular.module('rixi-service', []);

service.factory('User', function ($resource) {
    return $resource('rixi-rest/users/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });
});
