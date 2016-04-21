app = angular.module('rixi-app', [
    'ngRoute',
    'ngResource',
    'ngAnimate',
    'xeditable',
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
            .when('/statistic',
            {
                controller: 'LineCtrl',
                templateUrl: 'view/statistic.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
