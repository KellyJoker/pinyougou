//品牌服务层
app.service('brandService', function($http) {
	this.findAll = function() {
		return $http.get("/brand/findAll.do");
	}

	this.findPage = function(pageNum, pageSize) {
		return $http.post("/brand/findPage.do?pageNum=" + pageNum
				+ "&pageSize=" + pageSize);
	}

	this.add = function(entity) {
		return $http.post("/brand/add.do", entity);
	}

	this.update = function(entity) {
		return $http.post("/brand/update.do", entity);
	}

	this.findOne = function(id) {
		return $http.get("/brand/findOne.do?id=" + id);
	}

	this.delSelected = function(ids) {
		return $http.post("/brand/delete.do?ids=" + ids);
	}

	this.search = function(pageNum, pageSize, searchEntity) {
		return $http.post("/brand/search.do?pageNum=" + pageNum + "&pageSize="
				+ pageSize, searchEntity);
	}

});