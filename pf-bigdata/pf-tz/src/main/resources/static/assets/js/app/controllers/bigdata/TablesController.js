app.controller('TablesController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {
        /*每页行数*/
        $scope.pageSize = 10;
        /*当前页数*/
        $scope.currentPage = 1;

        $scope.$on('$viewContentLoaded', function() {
            $scope.loadData()
        });

        $scope.loadData = function () {
            $component.post($env.url + 'bigdata/tables/findBy',
                {page: $scope.currentPage, size: $scope.pageSize},
                function (resp) {
                    $scope.page = resp.data
            });
        }

        $scope.addEntity = function (){
            $scope.entity = {};
            $scope.column = {};
            $scope.editForm = {
                title: WinTitle.ADD_TABLE,
                isEdit: false,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };
            $component.dialog('#modal_edit');
        }

        $scope.editEntity = function (v, $event) {
            $scope.entity = $component.clone(v);
            $scope.editForm = {
                title: WinTitle.EDIT_TABLE,
                isEdit: true,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };

            $component.dialog('#modal_edit');
        }

        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'bigdata/tables/delete?id=' + v.id, null, function () {
                    $component.success(Message.DEL_SUCCESS)
                    $scope.loadData();
                })
            })
        }

        $scope.saveEntity = function () {
            $component.post($env.url + 'bigdata/tables/saveEntity', $scope.entity, function(response){
                $scope.loadData();
                $component.closeDialog('#modal_edit');
                $component.success(Message.SAVE_SUCCESS)
            })
        }

        $scope.showSource = function () {
            $scope.dropTitle = '请选择数据源';
            $component.get($env.url + 'bigdata/tables/getDataSources', function (resp) {
                //console.log(resp);
                $scope.dataSources = resp.data;
                $component.dialog('#modal_source');
            });
        }
        
        $scope.changeDataSource = function (ds) {
            console.log("--------------")
            if(ds){
                $scope.dropTitle = ds.name;
                $component.get($env.url + 'bigdata/tables/getTables/' + ds.id, function (resp) {
                    console.log(resp)
                    $scope.tables = resp.data;
                });
            }
        }
        /*导入表格*/
        $scope.importTable = function (table) {
            $component.confirm('确定导入选择的表格吗？', function () {
                $component.post($env.url + 'bigdata/tables/importTable', table, function (resp) {
                    console.log(resp)
                    $scope.loadData();
                })
            });
        }
        $scope.addColumn = function () {
            if(!$scope.entity.columns){
                $scope.entity.columns = [];
            }
            console.log($scope.column)
            $scope.column.isnull = $scope.column.isnull_bl ? 1 : 0;
            $scope.column.ispk = $scope.column.ispk_bl ? 1 : 0;
            $scope.entity.columns.push($component.clone($scope.column))
            console.log($scope.entity.columns)
            $scope.column = {};
        }
        $scope.editColumn = function (col) {
            col.isEdit = true;
            col.isnull_bl = col.isnull == 1;
            col.ispk_bl = col.ispk == 1;
        }
        $scope.saveColumn = function (col) {
            col.isEdit = false;
            col.isnull = col.isnull_bl ? 1 : 0;
            col.ispk = col.ispk_bl ? 1 : 0;
        }
        $scope.delColumn = function (index) {
            //删除当前索引的列
            $scope.entity.columns.splice(index, 1);
        }
    }
]).directive('mydire', ['$compile', function ($compile) {
    return {
        restrict: 'E',
        scope: {
            tableData:'='
        },
        template: '<span ng-class="{true:\'fa fa-minus\', false: \'fa fa-plus\'}[isOpen]" ng-model="tableData" ng-click="show($event)"></span>',
        link: function ($scope, element, attr) {
            $scope.isOpen = false;
            $scope.show = function ($event) {
                var row = angular.element($event.currentTarget).parent().parent().parent();
                if($scope.isOpen){
                    console.log("移除行")
                    row.next().remove();
                }else{
                    console.log("添加行")
                    row.after($compile('<tr><td colspan="6"><mydire2 table-data="tableData"></mydire2></td></tr>')($scope));

                }
                $scope.isOpen = $scope.isOpen == true ? false : true;
            };
            $scope.changeTab = function ($event) {
                console.log($event.currentTarget);
                $event.currentTarget.addClass('active');
            }
        }
    }
}]).directive('mydire2', ['$compile', function ($compile) {
    return {
        restrict: 'E',
        scope: {
            tableData:'='
        },
        templateUrl: 'views/bigdata/test.html'
    }
}]);