app.controller("loginController",function(loginService,$scope){

	$scope.showName=function(){
		loginService.showName().success(
			function(response){
				$scope.loginName=response.loginName;
			}
		)
	}
	
})
