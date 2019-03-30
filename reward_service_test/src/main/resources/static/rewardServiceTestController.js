module = angular.module('rewardServiceTestApp', []);
module.controller('RewardServiceTestController', ['$http', '$scope', function ($http, $scope) {

    //REST web service URLS
    var REST_BASE_URL = 'http://localhost:7000'
    var SAVE_EXERCISE_URL = '/exercises'

    var FIND_ALL_CATEGORY_URL ='/api/v1/categories';
    var FIND_ITEM_BY_CATEGORY_URL ='/api/v1/items/category';
    var FIND_ALL_ITEM_URL ='/api/v1/items';
    var CREATE_ITEM_URL = '/api/v1/item';
    var CREATE_CATEGORY_URL = '/api/v1/category'

    //Load initial categories and items
    $scope.onPageLoad = function(){
        findAllCategory();
        findAllItem();
    }

    $scope.users= [{"id":1, "name":"test", "countryISOCode":"UK", "currencyISOCode":"GBP" },
                    {"id":2, "name":"test1", "countryISOCode":"DE", "currencyISOCode":"EUR" }]

    $scope.initAddExercise = function(user){
        $scope.selectedUser = user;
        $scope.showSaveExerciseForm = true;
    }

    $scope.exercises = [];
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
                $scope.exercises.push($scope.savedExercise);
                $scope.exerciseSaveSuccessMessage = "Exercise saved successfully!"
                $scope.exercise = {};
                $scope.exerciseSaveErrorMessage = '';
            }

        }, function error(response) {
            $scope.exerciseSaveErrorMessage = response.data.errors[0].defaultMessage;;
            $scope.exerciseSaveSuccessMessage = '';
        });

    }

    //Create category
    $scope.createCategory = function(category){

        $http({
            method: 'POST',
            url: REST_BASE_URL+CREATE_CATEGORY_URL,
            data: typeof category !== 'undefined'?category:{}
        }).then(function succes(response) {

            $scope.createdCategory = response.data;

            if(typeof  $scope.createdCategory !== 'undefined' && $scope.createdCategory != null)
            {
                $scope.categories.push($scope.createdCategory);
                $scope.categoryCreateSuccessMessage = "Category created successfully!"
                $scope.category = {};
                $scope.categoryCreateErrorMessage = '';
            }

        }, function error(response) {
            $scope.categoryCreateErrorMessage = response.data.errors[0].defaultMessage;;
            $scope.categoryCreateSuccessMessage = '';
        });
    }

    //Find all category
    findAllCategory = function(){
        $http({
            method: "GET",
            url: REST_BASE_URL+FIND_ALL_CATEGORY_URL,
            params: {},
            headers: { 'Access-Control-Allow-Origin': '*' },
        }).then(function succes(response) {
            if(typeof  response.data !== 'undefined' && response.data.length > 0) {
                $scope.categories = response.data;
            }

        }, function error(response) {
        });
    }

    //Create item
    $scope.createItem = function(item){

        if(typeof $scope.selectedCategory !=='undefined'){
            item.categoryId = $scope.selectedCategory.id;
        }

        $http({
            method: "POST",
            url: REST_BASE_URL+CREATE_ITEM_URL,
            data: item,
        }).then(function succes(response) {

            $scope.createdItem = response.data;

            if(typeof  $scope.createdItem !== 'undefined' && $scope.createdItem != null)
            {
                $scope.items.push($scope.createdItem);

                $scope.itemCreateSuccessMessage = 'Item created successfully!';

                $scope.itemCreateErrorMessage = '';

                $scope.item = {};
            }

        }, function error(response) {
            $scope.itemCreateErrorMessage = response.data.errors[0].defaultMessage;
            $scope.itemCreateSuccessMessage = '';
        });
    }

    //Find all item
    findAllItem = function(){
        $http({
            method: "GET",
            url: REST_BASE_URL+FIND_ALL_ITEM_URL,
            params: {}
        }).then(function succes(response) {

            if(typeof  response.data !== 'undefined' && response.data.length > 0) {
                $scope.items = response.data;
            }

        }, function error(response) {
        });
    }

    //Find item by category
    $scope.findItemByCategory = function (category) {
        $scope.selectedCategory = category;
        $http({
            method: "GET",
            url: REST_BASE_URL+FIND_ITEM_BY_CATEGORY_URL+"/"+category.id,
            params: {}
        }).then(function succes(response) {
            if(typeof  response.data !== 'undefined') {
                $scope.items = response.data;
            }

        }, function error(response) {
        });
    }

    $scope.initializeMessages = function(){
        $scope.categoryCreateSuccessMessage = '';
        $scope.categoryCreateErrorMessage = '';

        $scope.itemCreateSuccessMessage = '';
        $scope.itemCreateErrorMessage = '';
    }

}]);