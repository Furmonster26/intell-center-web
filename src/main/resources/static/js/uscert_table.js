// AngularJS
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $interval, $http) {
	var m = $scope;
	
	m.myRoleName = {};
	
	m.myRoleName['ROLE_USER'] = '用戶';
	m.myRoleName['ROLE_WRITER'] = '撰寫員';
	m.myRoleName['ROLE_REVIEWER'] = '審核員';
	m.myRoleName['ROLE_ADMIN'] = '管理員';
	
	m.myRole = [];
	m.show = false;// show translation 

	m.posts = [];
	
	angular.element(document).ready(function () {
		$http({
			method : 'GET',
			url : '/uscert/init'
		}).then(
				function successCallback(response) {
					m.myRole = response.data.roles;
				}, function errorCallback(response) {
					console.log(response);
				});
    });
	
	$http({
		method : 'GET',
		url : '/uscert/list'
	}).then(
			function successCallback(response) {
				response.data.forEach(function(item, index) {
					var obj = angular.fromJson(item);
					obj.product = obj.product;
					obj.vendor = obj.vendor;
					obj.Score = obj.Score.substring(obj.Score.indexOf('>') + 1,
							obj.Score.indexOf('</'));
					obj.Published = obj.Published.substring(obj.Published
							.indexOf('>') + 1, obj.Published.indexOf('</'));
					if (obj.Score >= 7) {
						m.posts.push(obj);
					}
				});
			}, function errorCallback(response) {

			});
	
	m.clickSave = function(item) {
		item.show = !item.show;
		console.log(item.chi);
		
		var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            };
		
		var param = JSON.stringify({'postId': item._id.$oid, 'chi': item.chi});
		
		$http({
		    method: 'POST',
		    url: '/uscert/update',
		    data: param,
		    headers: {'Content-Type': 'application/json;'}
		  });
	}
	
	m.clickModify = function(item) {
		item.show = !item.show;
		console.log(item.chi);
	}
	
	m.clickApprove = function(item) {
		item.show = !item.show;
		item.approved = true;
		console.log(item.chi);
	}
	
	m.clickRelease = function(item) {
		item.show = !item.show;
		item.released = true;
		console.log(item.chi);
	}
	
	m.clickSubmit = function(item) {
		item.submit = true;
		console.log(item.chi);
		var param = JSON.stringify({'postId': item._id.$oid, 'chi': item.chi});
		
		$http({
		    method: 'POST',
		    url: '/uscert/submit',
		    data: param,
		    headers: {'Content-Type': 'application/json;'}
		  });
	}
	
	m.showWriter = function(item) {
		return (item.chi == undefined || item.chi.length === 0) && 
			(m.myRole.indexOf('ROLE_WRITER') > -1 || m.myRole.indexOf('ROLE_REVIEWER') > -1);	
	};
	
	m.showWriterBeforeSubmit = function(item) {
		return m.myRole.indexOf('ROLE_WRITER') > -1 && !item.submit;
	};
	
	m.showWriterAfterSubmit = function(item) {
		return m.myRole.indexOf('ROLE_WRITER') > -1 && item.submit;
	};
	
	m.showReviewerBeforeReleased = function(item) {
		return m.myRole.indexOf('ROLE_REVIEWER') > -1 && !item.released;
	};
	
	m.showReviewerAfterReleased = function(item) {
		return m.myRole.indexOf('ROLE_REVIEWER') > -1 && item.released;
	};
});