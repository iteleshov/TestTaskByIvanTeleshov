var REST_URL = '/rest/';

(function(angular) {
    var UserFactory = function($resource) {
        return $resource(REST_URL + ':id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    };

    UserFactory.$inject = ['$resource'];
    angular.module("application.services").factory("User", UserFactory);
}(angular));

