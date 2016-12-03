app = angular.module('rixi-app', [
    'ngRoute',
    'ngResource',
    'ngAnimate',
    'xeditable',
    'ui.bootstrap',
    'chart.js',
    'rixi-controller',
    'rixi-service'
]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3';
});

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/',
            {
                controller: 'UserCtrl',
                templateUrl: 'view/users.html'
            })
            .when('/contact',
            {
                templateUrl: 'view/contact.html'
            })
            .when('/about',
            {
                templateUrl: 'view/about.html'
            })
            .when('/statistic/views',
            {
                controller: 'LineCtrl',
                templateUrl: 'view/statistic.html',
                activeTab: 'views'
            })
            .when('/statistic/creates',
            {
                controller: 'LineCtrl',
                templateUrl: 'view/statistic.html',
                activeTab: 'creates'
            })
            .when('/statistic/updates',
            {
                controller: 'LineCtrl',
                templateUrl: 'view/statistic.html',
                activeTab: 'updates'
            })
            .when('/statistic/deletes',
            {
                controller: 'LineCtrl',
                templateUrl: 'view/statistic.html',
                activeTab: 'deletes'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('rixiHttpInterceptor');
});


app.factory('rixiHttpInterceptor', function ($q, $rootScope) {
    return {
        'request': function (config) {
            return config;
        },

        'requestError': function (rejection) {
            console.log(rejection);
            $rootScope.alerts.push({
                msg: 'Error: ' + rejection.statusText,
                type: 'danger'
            });
            return $q.reject(rejection);
        },

        'response': function (response) {
            return response;
        },

        'responseError': function (rejection) {
            $rootScope.alerts.push({
                msg: 'Error: ' + rejection.statusText,
                type: 'danger'
            });
            return $q.reject(rejection);
        }
    };
});
