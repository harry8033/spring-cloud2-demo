app.controller('TaskController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {
        /*每页行数*/
        $scope.pageSize = 10;
        /*当前页数*/
        $scope.currentPage = 1;
        /*初始化*/
        $scope.$on('$viewContentLoaded', function() {
            $scope.loadData()
        });
        /*加载列表数据*/
        $scope.loadData = function () {
            $component.post($env.url + 'schedule/task/findByPage',{
                page: $scope.currentPage,
                size: $scope.pageSize
            }, function (resp) {
                $scope.page = resp.data
            });
        };
        /*显示新增界面*/
        $scope.addEntity = function (){
            $scope.entity = {};
            $scope.editForm = {
                title: WinTitle.ADD_MANAGER,
                isEdit: false,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };
            $component.dialog('#modal_edit');
        };
        /*显示编辑界面*/
        $scope.editEntity = function (v, $event) {
            $scope.entity = $component.clone(v);
            $scope.editForm = {
                title: WinTitle.EDIT_MANAGER,
                isEdit: true,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };

            $component.dialog('#modal_edit');
        };
        /*删除*/
        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'schedule/task/remove',
                    {jobName: v.jobName, jobGroup: v.jobGroup},
                    function () {
                        $component.success(Message.DEL_SUCCESS)
                        $scope.loadData();
                })
            })
        };
        /*保存*/
        $scope.saveEntity = function () {
            $component.post($env.url + 'schedule/task/addJob', $scope.entity, function(){
                $scope.loadData();
                $component.closeDialog('#modal_edit');
                $component.success(Message.SAVE_SUCCESS)
            })
        };
        /*触发*/
        $scope.play = function (v, $event) {
            $component.post($env.url + 'schedule/task/trigger',
                {jobName: v.jobName, jobGroup: v.jobGroup},
                function () {
                    $component.success(Message.OPT_SUCCESS);
                    //$scope.loadData();
                });
        };
        /*暂停*/
        $scope.pause = function (v, $event) {
            $component.post($env.url + 'schedule/task/pause',
                {jobName: v.jobName, jobGroup: v.jobGroup},
                function () {
                    $component.success(Message.OPT_SUCCESS);
                    $scope.loadData();
                });
        };
        /*恢复*/
        $scope.resume = function (v, $event) {
            $component.post($env.url + 'schedule/task/resume',
                {jobName: v.jobName, jobGroup: v.jobGroup},
                function () {
                    $component.success(Message.OPT_SUCCESS);
                    $scope.loadData();
                });
        };
    }
]).filter('trigger_state', function () {
    return function (input) {
        if (input == 'PAUSED'){
            return '已暂停';
        }
        return '运行中';
    }
});