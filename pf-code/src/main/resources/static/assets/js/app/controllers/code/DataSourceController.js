app.controller('DataSourceController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {

        $scope.pageSize = 10;

        $scope.currentPage = 1;

        $scope.$on('$viewContentLoaded', function() {
            $scope.loadData()
        });

        $scope.loadData = function () {
            $component.post($env.url + 'code/datasource/findBy',{
                page: $scope.currentPage,
                size: $scope.pageSize
            }, function (resp) {
                $scope.page = resp.data
            });
        }

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
        }

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
        }

        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'code/datasource/delete',
                    {ids: [v.id]},
                    function () {
                        $component.success(Message.DEL_SUCCESS)
                        $scope.loadData();
                })
            })
        }

        $scope.saveEntity = function () {
            //console.log($scope.entity);
            $component.post($env.url + 'code/datasource/saveEntity', $scope.entity, function(response){
                $scope.loadData();
                $component.closeDialog('#modal_edit');
                $component.success(Message.SAVE_SUCCESS)
            })
        }
        
        $scope.viewTables = function (e) {
            $component.get($env.url + 'code/datasource/viewTables/' + e.id, function (resp) {
               console.log(resp);
            });
        }
    }
]);