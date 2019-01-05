//定义控制器
    	app.controller("brandController",function($scope,$http,$controller,brandService){
    		//继承
    		$controller('baseController',{$scope:$scope});//继承 
    		
    		// 定义方法-查询所有-为分页
    		$scope.findAll=function(){
    			// 向后台发送请求
    			brandService.findAll().success(
    				// 接收后台发过来的数据
    				function(response){
    					$scope.list=response;
    				}
    			)
    		};
    		
    		//查询所有-分页
    		$scope.findPage=function(pageNum,pageSize){
    			//发送后台请求
    			brandService.findPage(pageNum,pageSize).success(
    				//接收请求		
    				function(response) {
    					//每页显示的集合
						$scope.list=response.rowsList;
    					//更新总记录数
						$scope.paginationConf.totalItems=response.total;
					}
    			)
    			
    		};
    
    		// 添加品牌信息
    		$scope.save=function(){
    			var　object = null;
    			if($scope.entity.id==null){
    				object=brandService.add($scope.entity);
    			}else{
    				object=brandService.update($scope.entity);
    			}
    			object.success(
	    				function(response){
							if(response.flag){
								$scope.reloadList();
								alert(response.message);
							}else{
								alert(response.message);
							}    					
	    				}		
	    			);    			
    		}
    		
    		//根据id查找品牌信息
    		$scope.findOne=function(id){
    			brandService.findOne(id).success(
    				function(response){
    					$scope.entity=response;
    				}		
    			);
    		}

    		//删除
    		$scope.delSelected=function(){
    			
    			//如果数组长度大于0
    			if($scope.ids.length>0){
    				if(confirm("您确定要删除所选信息么?")){
    					brandService.delSelected($scope.ids).success(
        	    				function(response){
        	    					if(response.flag){
        	    						$scope.reloadList();
        	    						alert(response.message);
        	    					}else{
        	    						alert(response.message);
        	    					}
        	    				}
        	    		)
        			}
    			}
    			
    		}
    		
    		//定义搜索对象
    		$scope.searchEntity={};
    		
    		//模糊查询
    		$scope.search=function(pageNum,pageSize){
    			brandService.search(pageNum,pageSize,$scope.searchEntity).success(
    				function(response){
    					$scope.list=response.rowsList;
    					$scope.paginationConf.totalItems=response.total;
    				}		
    			);
    		}
    })