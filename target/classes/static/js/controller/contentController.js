//广告控制层（运营商后台）
app.controller("contentController", function ($scope,$controller, contentService) {

    //继承baseController,本质上继承的是$scope
    $controller('baseController',{$scope:$scope});

    $scope.peopleList = [];//广告集合
    $scope.findAll = function () {
        contentService.findAll().success(
            function (response) {
                $scope.peopleList = response;
            }
        );
    }
    $scope.findOne= function (pid) {
        contentService.findOne(pid).success(
            function (response) {
                $scope.people = response;
            }
        );
    }
    $scope.reloadList= function () {
        contentService.reloadList($scope.searchEntity.name).success(
            function (response) {
                $scope.peopleList = response;
            }
        );
    }


    $scope.searchEntity = {};
    /**
     * 条件查询
     */
    $scope.search=function (page, rows) {
        brandService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;

                //把后台的总条数给$scope.paginationConf
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

    $scope.save = function () {
        if ($scope.people.pid==null){
            contentService.save($scope.people).success(
                function (response) {
                    if(angular.equals(response,"success")){
                        $scope.findAll();
                    }
                }
            );
        }else {
            contentService.edit($scope.people.pid,$scope.people).success(
                function (response) {
                    if(angular.equals(response,"success")){
                        $scope.findAll();
                    }
                }
            );

        }

    }

    //初始化复选框值的array
    $scope.selectIds=[];
    /**
     * 选中取消,selectIds增加删除
     * 如何判断是选中还是取消:angularJs提供了内置的对象$event中可以去除是勾选还是取消
     */
    $scope.updateSelection=function ($event, pid) {
        if($event.target.checked){//勾选
            $scope.selectIds.push(pid);
        }else{//取消
            //根据id找到对应的index
            var index = $scope.selectIds.indexOf(pid);
            //根据index从数组中删除
            $scope.selectIds.splice(index, 1);
        }
    }

    //扩展
    $scope.selectAll = function () {

        if ($scope.select_all) { //判断是全选
            $scope.selectIds = [];//先清空，防止在操作了一个轮回之后，重复添加了...
            angular.forEach($scope.peopleList, function (i) {  //list.devices这是循环从后台获取的数组，并添加到刚刚定义的数组里
                i.checked = true; //全选即将所有的复选框变为选中
                $scope.selectIds.push(i.pid);
            })
        }else{
            angular.forEach($scope.peopleList, function (i) {
                i.checked = false; //所有复选框为不选中
                $scope.selectIds = [];    })
        }
    }

    $scope.dele= function () {
        if ($scope.selectIds.length==0){
            alert("请先选择");
        }else {
            confirm("确定要删除？")
            contentService.dele($scope.selectIds).success(
                function (response) {
                    $scope.findAll();
                    $scope.selectIds = [];
                }
            );
        }
    }

});