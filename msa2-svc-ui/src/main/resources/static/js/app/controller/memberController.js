'use strict';

angular.module('oauthApp')
    .controller('memberCtrl', function ($scope, $location, $http, dataService) {
        
        // Assigns data from the user service into "token"
        // variables in controller scope. 
        var assignAllUserDataToScope = function (data) {
            $scope.memberDataArray = data;
        };

        var logError = function (error) {
            console.log(error);
        };

		// Assign data for a single user
		var assignUserDataToScope = function (data) {
            $scope.memberData = data;
        };
            
        // Method exposed to get specific user data
        this.getMemberByName = function (userName) {
        	dataService.getMemberByName(userName)
        		.then(assignUserDataToScope, logError);
        };   
        
        //When the script loads, get all the user's data
        dataService.getMemberList()
            .then(assignAllUserDataToScope, logError);
    });