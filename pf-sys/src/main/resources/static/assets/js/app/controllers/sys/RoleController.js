app.controller('RoleController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {
        /*每页行数*/
        $scope.pageSize = 10;
        /*当前页数*/
        $scope.currentPage = 1;

        /*初始化*/
        $scope.$on('$viewContentLoaded', function() {
            $scope.loadData();
            $scope.loadTree();
        });
        /*加载列表数据*/
        $scope.loadData = function () {
            $component.post($env.url + 'sys/role/findByPage',{
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
            //清空树上所有的选择
            $scope.inst.deselect_all(false);
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
            //清空树上所有的选择
            $scope.inst.deselect_all(false);
            //获取已选中节点，并将已选择节点默认勾选上
            $component.get('sys/role/getSelectedPrivTree/' + v.id, function(response){
                if(response){
                    $scope.inst.select_node(response.data);
                }
            })

            $component.dialog('#modal_edit');
        };
        /*删除*/
        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'sys/role/delete',
                    {ids: [v.id]},
                    function () {
                        $component.success(Message.DEL_SUCCESS)
                        $scope.loadData();
                })
            })
        };
        /*保存*/
        $scope.saveEntity = function () {
            //获取选中的节点
            var nodes = $scope.inst.get_selected(true);
            console.log(nodes);
            var ids = [];
            for (var i = 0, j = nodes.length; i < j; i++) {
                var node = nodes[i];
                if ($scope.inst.is_leaf(node)) {
                    ids.push(node.id);
                }
            }
            if(ids){
                $scope.entity.privileges = ids.join(',');
            }
            console.log(ids);
            $component.post($env.url + 'sys/role/saveEntity', $scope.entity, function(response){
                $scope.loadData();
                $component.closeDialog('#modal_edit');
                $component.success(Message.SAVE_SUCCESS)
            })
        };

        $scope.loadTree = function () {
            $scope.privTree = angular.element("#priv_tree").jstree({
                "checkbox" : {
                    "keep_selected_style" : false
                },
                "plugins" : [ "checkbox", "json_data" ],
                "core" : {
                    "data" : {
                        'url' : 'sys/role/getPrivTree',
                        'dataType' : 'json'
                    }
                }
            }).on("ready.jstree", function (e, data) {
                data.instance.open_all();
            }).on('changed.jstree', function(e, data) {

            }).on('loaded.jstree', function(e, data){
                $scope.inst = data.instance;
            });
        }
    }
]);