var umsApp = angular.module('ums', []);

umsApp.controller('RegistrationCtl', ['$scope', '$http', function($scope, $http) {
        
      //Submitt button
      $scope.submit = function() {
        if ($scope.text) {
            var data=$scope.fields; 
            $http.post('users/', data).success(function(data) {
              alert('user created!');
           });
            
        }
      };
      
    }]);