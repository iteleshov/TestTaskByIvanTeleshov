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

        $scope.dateRangeFilter = function (property, startDate, endDate) {
            return function (item) {
                if (item[property] === null) return false;
                if (startDate === undefined && endDate === undefined) return true;

                var itemDate = moment(item[property]);
                var start;
                var end;

                if (endDate === undefined) {
                    start = moment(startDate, "YYYY-MM-DD");
                    return itemDate >= start;
                }
                else if (startDate === undefined) {
                    end = moment(endDate, "YYYY-MM-DD");
                    return itemDate <= end;
                }
                else {
                    start = moment(startDate, "YYYY-MM-DD");
                    end = moment(endDate, "YYYY-MM-DD");
                    if (itemDate >= start && itemDate <= end) return true;
                    return false;
                }
            }
        }
    };

    AppController.$inject = ['$scope', 'User'];
    angular.module("application.controllers").controller("AppController", AppController);
}(angular));