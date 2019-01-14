 //控制层 
app.controller('goodsController' ,function($location,$scope,$controller,uploadService,goodsService,itemCatService,typeTemplateService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//商品状态
	$scope.status=['未审核','已审核','审核未通过','关闭'];
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	$scope.ItemCatList=[];//定义数组
	$scope.findItemCatList=function(){
		itemCatService.findAll().success(
			function(response){
				for(var i = 0; i < response.length; i++){
					$scope.ItemCatList[response[i].id]=response[i].name;
				}
			}
		)
	}
	
	//查询实体 
	$scope.findOne=function(){	
		var id = $location.search()['id'];//获取参数值
		//alert(id);
		if(id==null){
			return;
		}
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;
				//向富文本编辑器添加商品介绍
				editor.html($scope.entity.goodsDesc.introduction);
				//显示图片列表
				$scope.entity.goodsDesc.itemImages=JSON.parse($scope.entity.goodsDesc.itemImages);
				// 显示扩展属性
				$scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.entity.goodsDesc.customAttributeItems);
				//规格列表
				$scope.entity.goodsDesc.specificationItems=JSON.parse($scope.entity.goodsDesc.specificationItems);
				//SKU 列表规格列转换 
				for( var i=0;i<$scope.entity.itemList.length;i++ ){
					$scope.entity.itemList[i].spec = JSON.parse( $scope.entity.itemList[i].spec);
				} 
			}
		);				
	}
	
	$scope.checkAttributeValue=function(specName,optionName){
		var item = $scope.entity.goodsDesc.specificationItems;
		var object = $scope.searchObjectByKey(item,'attributeName',specName)
		if(object==null){
			return false;
		}
		
		if(object.attributeValue.indexOf(optionName)>=0){
			return true;
		}
		return false;
	}
	
	//保存 
	$scope.save=function(){
		$scope.entity.goodsDesc.introduction=editor.html();//获取富文本编辑器内容
		var serviceObject;//服务层对象
		
		if($scope.entity.goods.id!=null){//如果有 ID
			serviceObject=goodsService.update( $scope.entity ); //修改
		}else{
			serviceObject=goodsService.add( $scope.entity );//增加
		} 
		serviceObject.success(
			function(response){
				if(response.flag){
					location.href="goods.html";//跳转到商品列表页
					alert(response.message)
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//文件上传
	$scope.upload=function(){
		uploadService.upload().success(
			function(response){
				if(response.flag){
					//alert(response.message);
					//如果文件上传成功,设置文件地址,在页面上显示该图片
					$scope.image_entity.url=response.message;
				}else{
					alert(response.message);
				}
			}
		)
	}
	
	$scope.entity={goodsDesc:{itemImages:[],specificationItems:[]}};//定义页面实体结构
	//添加图片
	$scope.add_image_entity=function(){
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
		
	}
	
	//获取以及分类下拉列表的值
	$scope.selectItemCat1List=function(){
		itemCatService.findByParentId(0).success(
			function(response){
				//alert(response.name);
				$scope.itemCat1List=response; 
			}
		)
	}
	
	//读取二级分类
	$scope.$watch('entity.goods.category1Id',function(newValue,oldValue){
		itemCatService.findByParentId(newValue).success(
				function(response){
					$scope.itemCat2List=response; 
				}
			)
	})
	
	//读取三级分类
	$scope.$watch('entity.goods.category2Id',function(newValue,oldValue){
		itemCatService.findByParentId(newValue).success(
				function(response){
					$scope.itemCat3List=response; 
				}
			)
	})
	
	//读取模板id
	$scope.$watch('entity.goods.category3Id',function(newValue,oldValue){
		itemCatService.findOne(newValue).success(
				function(response){
					$scope.entity.goods.typeTemplateId=response.typeId; 
				}
			)
	})
	
	//查询模板中的品牌信息
	$scope.$watch('entity.goods.typeTemplateId',function(newValue,oldValue){
		typeTemplateService.findOne(newValue).success(
			function(response){
				$scope.typeTemplate=response;
				if($location.search()['id']==null){
					$scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.typeTemplate.customAttributeItems);
				}
				$scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);
			}
		)
		
	})
	
	//查询规格列表
	$scope.$watch('entity.goods.typeTemplateId',function(newValue,oldValue){
		typeTemplateService.findSpecList(newValue).success(
			function(response){
				$scope.specList=response;
			}
		);
	})
	

	$scope.updateSpecAttribute = function($event, name, value) {
		var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName',name);
		if (object != null) {
			if ($event.target.checked) {
				object.attributeValue.push(value);
			} else {// 取消勾选
				object.attributeValue.splice(object.attributeValue.indexOf(value), 1);// 移除选项
				// 如果选项都取消了，将此条记录移除
				if (object.attributeValue.length == 0) {
					$scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object), 1);
				}
			}
		} else {
			$scope.entity.goodsDesc.specificationItems.push({"attributeName" : name,"attributeValue" : [ value ]
			});
		}
	}
	
	//生成sku表
	$scope.createItemList=function(){
		$scope.entity.itemList=[{spec:{},price:0,num:0,status:'0',isDefault:'0' }];//初始化表格
		var items= $scope.entity.goodsDesc.specificationItems;
		for(var i=0;i< items.length;i++){
			$scope.entity.itemList =addColumn($scope.entity.itemList,items[i].attributeName,items[i].attributeValue);
		}
	}

	//添加列值
	addColumn=function(list,columnName,conlumnValues){
		var newList=[];//新的集合
		for(var i=0;i<list.length;i++){
			var oldRow= list[i];
			for(var j=0;j<conlumnValues.length;j++){
				var newRow= JSON.parse( JSON.stringify( oldRow ) );//深克隆
				newRow.spec[columnName]=conlumnValues[j];
				newList.push(newRow);
			}
		}
		return newList;
	}
})
