app.controller('ManagerController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {

        $scope.pageSize = 10;

        $scope.currentPage = 1;

        $scope.$on('$viewContentLoaded', function() {
            console.debug('info', '进入管理员管理页面')
            $scope.loadData()
            $scope.loadRoles()
        });

        $scope.loadData = function () {
            $component.post($env.url + 'sys/manager/findBy',
                {page: $scope.currentPage, size: $scope.pageSize},
                function (resp) {
                    $scope.page = resp.data
                });
        }

        $scope.loadRoles = function () {
            $scope.roles = [];
            $component.get($env.url + 'sys/role/findBy', function (resp) {
                angular.forEach(resp.data, function(data){
                    $scope.roles.push({id:data.id, text:data.rolename});
                })
                $scope.roleField = angular.element("#roles").select2({
                    placeholder: "请选择角色",
                    allowClear: true,
                    data: $scope.roles
                });
            });
        }

        $scope.addEntity = function (){
            $scope.entity = {status:false};
            $scope.editForm = {
                title: WinTitle.ADD_MANAGER,
                isEdit: false,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };
            $scope.roleField.empty();
            $scope.roleField.select2({
                placeholder: "请选择角色",
                allowClear: true,
                data: $scope.roles
            });
            $component.dialog('#modal_edit');
        }

        $scope.editEntity = function (v, $event) {
            $scope.entity = $component.clone(v);
            $scope.entity.status = v.state == 1;
            $scope.editForm = {
                title: WinTitle.EDIT_MANAGER,
                isEdit: true,
                save: function ($event) {
                    $scope.saveEntity()
                }
            };
            if(v.roleList){
                let selectedRoles = [];
                angular.forEach(v.roleList,function(role){
                    selectedRoles.push(role.id);
                })
                $scope.roleField.val(selectedRoles).trigger('change');
            }

            $component.dialog('#modal_edit');
        }

        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'sys/manager/deleteEntity',
                    {ids: [v.id]},
                    function () {
                        $component.success(Message.DEL_SUCCESS)
                        $scope.loadData();
                    })
            })
        }

        $scope.saveEntity = function () {
            let selectRoles = $scope.roleField.select2("data");
            let tempRoles = [];
            angular.forEach(selectRoles, function(data){
                tempRoles.push(data.id);
            })
            $scope.entity.roles = tempRoles.join(',');
            $scope.entity.state = $scope.entity.status ? 1:0;
            $component.post($env.url + 'sys/manager/saveEntity', $scope.entity, function(response){
                    $scope.loadData();
                    $component.closeDialog('#modal_edit');
                    $component.success(Message.SAVE_SUCCESS)
                })
        }

        $scope.reset = function (v) {
            $component.confirm(Message.RESET_PWD_CONFIRM, function () {
                $component.post($env.url + 'sys/manager/reset', {id: v.id, account: v.account}, function () {
                    $component.success(Message.RESET_PWD_SUCCESS)
                    $scope.loadData();
                })
            })
        }

}]).filter('roles', function () {
    return function (input) {
        let out = []
        angular.forEach(input, function (item) {
            out.push(item.rolename)
        })
        return out.join(',')
    }
});