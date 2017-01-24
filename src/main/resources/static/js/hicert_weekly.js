// AngularJS
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $sce, $interval, $http) {
	var m = $scope;
	m.msg = 'aaa';
	
	m.myRoleName = {};
	
	m.myRoleName['ROLE_USER'] = '用戶';
	m.myRoleName['ROLE_WRITER'] = '撰寫員';
	m.myRoleName['ROLE_REVIEWER'] = '審核員';
	m.myRoleName['ROLE_ADMIN'] = '管理員';
	
	m.myRole = [];
	m.show = false;// show translation
	m.writerDone = false;
	
	var beforeOneWeek = new Date(new Date().getTime() - 60 * 60 * 24 * 7 * 1000)
	  , day = beforeOneWeek.getDay()
	  , diffToMonday = beforeOneWeek.getDate() - day + (day === 0 ? -6 : 1)
	  , lastMonday = new Date(beforeOneWeek.setDate(diffToMonday))
	  , lastSunday = new Date(beforeOneWeek.setDate(diffToMonday + 6));
	m.thisSunday = lastSunday;
	m.lastMonday = lastMonday;

	m.vuls = [];
	
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
		url : '/uscert/hicert/list'
	}).then(
			function successCallback(response) {
				
				console.log(response);
								
				for (var key in response.data) {
					var obj = {};
					obj.vendor = key.split(' -- ')[0];
					obj.product = key.split(' -- ')[1];
					obj.description = response.data[key].description;
					obj.size = response.data[key].size;
					obj.cves = $sce.trustAsHtml(response.data[key].cves[0]);
//					$sce.trustAsHtml(someHtmlVar);
					
					m.vuls.push(obj);
				}
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
	
	m.clickSubmit = function() {
//		item.submit = true;
//		console.log(item.chi);
		// check all posts have chi
		if (m.posts.some(x => x.chi == undefined || x.chi.length === 0)) {
			alert('還有弱點還沒翻譯');
			return;
		}
		
		m.posts.map(p => {
			var param = JSON.stringify({'postId': p._id.$oid, 'chi': p.chi});
			
			$http({
			    method: 'POST',
			    url: '/uscert/submit',
			    data: param,
			    headers: {'Content-Type': 'application/json;'}
			  });
		});
	}
	
	m.updateWriterDone = function() {
		m.writerDone  = !m.posts.some(x => x.chi == undefined || x.chi.length === 0);
	};
	
	m.showWriter = function(item) {
		return (item.chi == undefined || item.chi.length === 0) && 
			(m.myRole.indexOf('ROLE_WRITER') > -1 || m.myRole.indexOf('ROLE_REVIEWER') > -1);	
	};
	
	m.showWriterBeforeSubmit = function(item) {
		return m.myRole.indexOf('ROLE_WRITER') > -1 && !m.writerDone;
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