//广告控制层（运营商后台）
app.controller("contentController", function ($scope,$controller, contentService) {

    //继承baseController,本质上继承的是$scope
    $controller('baseController',{$scope:$scope});

    $scope.list = [];//集合
    $scope.findAll = function () {
        contentService.findAll().success(
            function (response) {
                $scope.list = response;
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
    $scope.searchEntity = {};
    /**
     * 条件查询
     */
    $scope.search=function (page, rows) {
        contentService.search(page, rows, $scope.searchEntity).success(
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
                    if(response.success){
                        alert(response.message);
                        $scope.searchEntity = {};
                        $scope.reloadList();
                    }else {
                        alert(response.message);
                        $scope.searchEntity = {};
                        $scope.reloadList();
                    }
                }
            );
        }else {
            contentService.edit($scope.people.pid,$scope.people).success(
                function (response) {
                    if(response){
                        $scope.searchEntity = {};
                        $scope.reloadList();
                    }
                }
            );

        }

    }
    $scope.dele= function () {
        if ($scope.selectIds.length==0){
            alert("请先选择");
        }else {
            if(confirm("确定要删除？")){
                contentService.dele($scope.selectIds).success(
                    function (response) {
                        alert("删除成功！");
                        $scope.searchEntity = {};
                        $scope.reloadList();
                        $scope.selectIds = [];
                    }
                );
            }else {
                $scope.reloadList();
                $scope.selectIds = [];

            }
        }
    }

});