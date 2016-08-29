(function(angular) {
    angular.module("application.controllers", []);
    angular.module("application.services", []);
    angular.module("application", ["ngResource", "application.controllers", "application.services"]);
}(angular));