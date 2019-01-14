app.service('uploadService',function($http){
	//文件上传
	this.upload=function(){
		var formData = new FormData();
		formData.append('files',file.files[0]);
		return $http({
			method:'post',
			url:'/uploadController/upload.do',
			data:formData,
			headers:{'Content-Type':undefined},
			transformRequest: angular.identity
		});
	}
	
})