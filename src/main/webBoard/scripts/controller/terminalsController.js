/**
 * Created by marwen on 15/12/15.
 */
'use strict';
firstBackApp.controller('terminalsController', function ($scope, $http) {

    $scope.results = [];
    $scope.errors = [];

    var setDate = function (objectResult) {
        if (angular.isDefined(objectResult) && angular.isArray(objectResult)) {
            objectResult.forEach(function (result) {
                result.date = new Date();
            });
        } else if (angular.isDefined(objectResult) && !angular.isArray(objectResult)) {
            objectResult.date = new Date();
        }
    };

    $scope.listTerminals = function () {
        $scope.date = new Date();

        $scope.isTerminalLoading = true;

        $http.get('/terminals').
            success(function (data) {
                setDate(data);
                $scope.results.push(data);
                //$scope.isTerminalLoading = false;
            }).error(function (data) {
                setDate(data);
                $scope.errors.push(data);
                //$scope.isTerminalLoading = false;
            });
    };
});
