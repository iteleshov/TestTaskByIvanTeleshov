(function(angular) {
    var AppController = function($scope, User) {
        User.query(function(response) {
            $scope.users = response ? response : [];
        });

        $scope.addUser = function(description) {
            new User({
                description: description,
                checked: false
            }).$save(function(user) {
                $scope.users.push(user);
            });
            $scope.newUser = "";
        };

        $scope.updateUser = function(user) {
            user.$update();
        };

        $scope.deleteUser = function(user) {
            user.$remove(function() {
                $scope.users.splice($scope.users.indexOf(user), 1);
            });
        };
    };

    AppController.$inject = ['$scope', 'User'];
    angular.module("application.controllers").controller("AppController", AppController);
}(angular));