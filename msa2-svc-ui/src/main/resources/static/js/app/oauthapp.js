'use strict';

angular.module('oauthApp', ['ngRoute'])
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'views/home.html',
            controller: 'homeCtrl'
        }).when('/member', {
            templateUrl: 'views/member.html',
            controller: 'memberCtrl',
            controllerAs: 'memberController'
        }).when('/order', {
            templateUrl: 'views/order.html',
            controller: 'orderCtrl',
            controllerAs: 'orderController'
        }).otherwise('/');

        //Custom header is needed starting angular 1.3; else Spring security might pop authentication dialog
        // by sending the WWW-Authenticate header field in the 401 Unauhorized HTTP response
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    })
    .directive("orderComments", function () {
        return {
            restrict: 'E',
            scope: {
                orderComments: '=comments'
            },
            templateUrl: "views/order-comments.html"
        };
    })
    .directive("orderDetails", function () {
        return {
            restrict: 'E',
            templateUrl: "views/order-details.html"
        };
    });
