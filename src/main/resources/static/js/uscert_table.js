// AngularJS
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $interval, $http) {
	var m = $scope;

	var time = new Date();
	// m.attackTime = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" +
	// time.getDate() + " " + ("0" + (time.getHours() - 1)).slice(-2);
	m.attackTime = '2016-11-18 20';

	m.typeSel = 'name';
	m.ipsVol = 2000;
	m.dnsVol = 1234567;
	m.malwareVol = 283473;

	m.show = false;

	m.posts = [];
	$http({
		method : 'GET',
		url : '/uscert/list'
	}).then(
			function successCallback(response) {
				response.data.forEach(function(item, index) {
					var obj = angular.fromJson(item);
					obj['product'] = obj.vendor.substring(obj.vendor
							.indexOf('- ') + 2, obj.vendor.length - 1);
					obj['vendor'] = obj.vendor.substring(0, obj.vendor
							.indexOf(' --'));
					obj.Score = obj.Score.substring(obj.Score.indexOf('>') + 1,
							obj.Score.indexOf('</'));
					obj.Published = obj.Published.substring(obj.Published
							.indexOf('>') + 1, obj.Published.indexOf('</'));
					if (obj.Published >= 7) {
						m.posts.push(obj);
					}
				});
			}, function errorCallback(response) {

			});
});