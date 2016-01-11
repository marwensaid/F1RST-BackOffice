/**
 * Created by marwen on 15/12/15.
 */
'use strict';
var firstBackApp = angular.module('firstBackApp',['ngRoute', 'ngSanitize', 'ngResource', 'ui.grid', 'ui.grid.selection']);
firstBackApp.config(
    function ($routeProvider) {
        $routeProvider.when('/terminals', {
            templateUrl: "/views/terminals.html",
            controller: 'terminalsController'
        });
    }
).run(function () {

    });

$('.navbar-collapse ul li a').click(function () {
    $('.navbar-toggle:visible').click();
});
