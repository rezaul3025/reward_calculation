module = angular.module('rewardServiceTestApp', []);
module.controller('RewardServiceTestController', ['$http', '$scope', function ($http, $scope) {

    //REST web service URLS
    var REST_BASE_URL = 'http://localhost:7000'
    var SAVE_EXERCISE_URL = '/exercises'
    var CALCULATE_REWARD_FOR_USER_URL = '/rewards/user'
    var FIND_EXERCISE_FOR_USER_URL = '/exercises/user'

    $scope.users= [{"id":1, "name":"test", "countryISOCode":"UK", "currencyISOCode":"GBP" },
                    {"id":2, "name":"test1", "countryISOCode":"DE", "currencyISOCode":"EUR" }]

    $scope.initAddExercise = function(user){
        $scope.selectedUser = user;
        $scope.showSaveExerciseForm = true;
        $scope.exerciseSaveSuccessMessage = '';
    }

    $scope.saveExercise = function(exercise){
        exercise['userId'] = $scope.selectedUser.id

        $http({
            method: 'POST',
            url: REST_BASE_URL+SAVE_EXERCISE_URL,
            data: typeof exercise !== 'undefined'?exercise:{}
        }).then(function succes(response) {

            $scope.savedExercise = response.data;

            if(typeof  $scope.savedExercise !== 'undefined' && $scope.savedExercise != null)
            {
                $scope.exerciseSaveSuccessMessage = "Exercise saved successfully!"
                $scope.exerciseSaveErrorMessage = '';
            }

        }, function error(response) {
            $scope.exerciseSaveErrorMessage = response.data.errors[0].defaultMessage;;
            $scope.exerciseSaveSuccessMessage = '';
        });

    }

    $scope.calculateRewardForUser = function(userId){
        $http({
            method: "GET",
            url: REST_BASE_URL+CALCULATE_REWARD_FOR_USER_URL+'/'+userId,
            params: {}
        }).then(function succes(response) {
            if(typeof  response.data !== 'undefined' && response.data.length > 0) {
                $scope.calculatedRewards = response.data;
                $scope.selectedUser = userId;
            }

        }, function error(response) {
        });
    }

    $scope.showExerciseAndRewardForUser = function(userId){
        $http({
            method: "GET",
            url: REST_BASE_URL+FIND_EXERCISE_FOR_USER_URL+'/'+userId,
            params: {}
        }).then(function succes(response) {
            if(typeof  response.data !== 'undefined' && response.data.length > 0) {
                $scope.exercises = response.data;
                $scope.selectedUser = user;
            }

        }, function error(response) {
        });
    }

}]);