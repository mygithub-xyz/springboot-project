//广告管理服务层
app.service("contentService", function ($http) {
    //根据分类ID查询广告列表
    this.findAll = function () {
        return $http.get("people/findAll");
    }
    this.findOne = function (pid) {
        return $http.get("people/getOne?pid="+pid);
    }
    this.reloadList = function (pname) {
        return $http.get("people/findByPnameLike?pname="+pname);
    }

    this.save = function (people) {
        return $http.post("people/add",people);
    }
    this.edit = function (pid,people) {
        return $http.post("people/edit?pid="+pid,people);
    }
    this.dele = function (selectIds) {
        return $http.post("people/delete?selectIds="+selectIds);
    }
});