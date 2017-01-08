/**
 * 
 */

//Define the Module
var employeeServiceApp = angular.module('employeeServiceApp', [
  'ngRoute',
  'employeeServiceControllers'
]);

employeeServiceApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


employeeServiceApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);

// Define the Routing
employeeServiceApp.config(['$routeProvider',
                    function($routeProvider) {
                      $routeProvider.
                        when('/viewEmployee', {
                          templateUrl: 'partials/viewEmployee.html',
                          controller: 'viewEmployeeCtrl'
                        }).
                        when('/addEmployee', {
                          templateUrl: 'partials/addEmployee.html',
                          controller: 'addEmployeeCtrl'
                        }).
                        when('/fileUploadForm', {
                            templateUrl: 'partials/fileUploadForm.html',
                            controller: 'fileUploadFormCtrl'
                          }).
                        otherwise({
                          redirectTo: '/viewEmployee'
                        });
                    }]);

//Define the Controllers
var employeeServiceControllers = angular.module('employeeServiceControllers', []);

//fileupload controller
employeeServiceControllers.controller('fileUploadFormCtrl', ['$scope', '$http','fileUpload',
   function ($scope, $http, fileUpload) {
	$scope.uploadFile = function(){
		alert('in uploadFile');
		var file = $scope.myFile;
		var uploadUrl = 'http://localhost:8181/restangular/rest/uploadFile';
		fileUpload.uploadFileToUrl(file, uploadUrl);
		$scope.successMsg = "file uploaded";
	}
                                                           }]);

//viewController
employeeServiceControllers.controller('viewEmployeeCtrl', ['$scope', '$http','$window','$sce',
  function ($scope, $http, $window, $sce) {
	
	$scope.downloadFile = function(){
		alert('in download file');
		//$window.location.href = "http://localhost:8181/restangular/rest/downloadFile";
	    $http.get('http://localhost:8181/restangular/rest/downloadFile/'+$scope.fileId,{responseType: 'arraybuffer'}).then(function(data) {
	    	//alert(JSON.stringify(data));
	    	alert('successfully got the file');
	    	
	    	//http://stackoverflow.com/questions/21628378/angularjs-display-blob-pdf-in-an-angular-app
	    	var fileName = "test.pdf";
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            
	    	//var file = new Blob([data.data], {type: 'application/pdf'});// This will open the file inline
            //http://stackoverflow.com/questions/5648967/force-browser-to-download-image-with-javascript-window-open
            var file = new Blob([data.data], {type: 'application/octet-stream'});//this will make the file downloaded and not opened directly
	        var blobUrl = URL.createObjectURL(file);
	        
	        //a.href = blobUrl;
            //a.download = fileName;
            //a.click();
	        $window.open(blobUrl, "Traffic_Violations.csv");
	        //$scope.content = $sce.trustAsResourceUrl(blobUrl);
	    }, function(data, status){
	    	//alert(status);
	    	//alert(data);
	    	$scope.errMsg = "Error downloaing file";
	    });
	};
	
	$scope.downloadFileWoBlob = function(){
		$window.open('http://localhost:8181/restangular/rest/downloadFile/'+$scope.fileId, "Traffic_Violations.csv");
	   
	};
	
    $http.get('http://localhost:8181/restangular/rest/employeeService').success(function(data) {
    	//alert(JSON.stringify(data));
    	$scope.employees = data;
    });
  }]);

//add Controller
employeeServiceControllers.controller('addEmployeeCtrl', ['$scope', '$http', '$routeParams',
  function($scope, $http, $routeParams) {
    $scope.submitEmployee = function(){
    	var dataObj = {
    			"id":$scope.id,
    			"firstName":$scope.fName,
    			"lastName":$scope.lName,
    			"address":{
    				"street":$scope.street,
    				"city":$scope.city,
    				"state":$scope.state,
    				"zip":$scope.zip}
		};	
    	alert(JSON.stringify(dataObj));
    	
		var res = $http.post('http://localhost:8181/restangular/rest/employeeService', dataObj);
		res.success(function(data, status, headers, config) {
			//alert('response msg::'+JSON.stringify(data));
			$scope.successMsg = data;
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
    	
		//Different way of posting data
		/*    	var req = {
		 method: 'POST',
		 url: 'http://localhost:9090/restangular/rest/employeeService/addEmployee',
		 headers: {
		   'Content-Type': 'application/json'
		 },
		 data: dataObj
		}

		$http(req).then(function(data){
				//alert( "success message: " + JSON.stringify({}));	
				$scope.successMsg = data.data;
			}, 
		function(data){
				alert( "failure message: " + JSON.stringify({data: data}));
			});*/
		
    };//end submitEmployee
  }]);