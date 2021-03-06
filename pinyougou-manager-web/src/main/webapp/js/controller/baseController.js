app.controller('baseController',function($scope){
	//重新加载
	$scope.reloadList = function() {
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}

	// 分页控件配置
	$scope.paginationConf = {
		currentPage : 1, // 当前页码
		totalItems : 10, // 总记录数
		itemsPerPage : 10, // 每页显示的条数
		perPageOptions : [ 5, 15, 20, 25, 30 ],// 每页显示条数的选项
		onChange : function() {// 更改页面时触发的事件
			$scope.reloadList();// 重新加载
		}
	}

	$scope.ids=[];
	//更新id数组
	$scope.updateIds=function($event,id){
		if($event.target.checked){
			$scope.ids.push(id);
		}else{
			var index=$scope.ids.indexOf(id);
			$scope.ids.splice(index,1);
		}
		
	}
	
	//提取 json 字符串数据中某个属性，返回拼接字符串 逗号分隔
	$scope.jsonToString = function(jsonString, key) {
		var json = JSON.parse(jsonString);// 将 json 字符串转换为 json 对象
		var value = "";
		for (var i = 0; i < json.length; i++) {
			if (i > 0) {
				value += ","
			}
			value += json[i][key];
		}
		return value;
	}
	
	//从集合中按照 key 查询对象
	$scope.searchObjectByKey=function(list,key,keyValue){
		//遍历集合
		for(var i = 0;i < list.length;i++){
			if(list[i][key]==keyValue){
				return list[i];
			}
		}
		return null;
	}
	
	
})