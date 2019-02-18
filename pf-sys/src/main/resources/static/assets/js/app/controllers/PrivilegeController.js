app.controller('PrivilegeController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {

        $scope.$on('$viewContentLoaded', function() {
            $scope.loadData()
        });

        $scope.loadData = function () {
            $component.get($env.url + 'sys/privilege/findBy', function (resp) {
                $scope.page = resp.data
            });
        }

        $scope.addEntity = function (){
            $scope.entity = {};
            $scope.editForm = {
                title: WinTitle.ADD_MENU,
                isEdit: false,
                save: function () {
                    $component.post($env.url + 'sys/privilege/saveEntity', $scope.entity, function(response){
                        $scope.loadData();
                        $component.closeDialog('#modal_edit');
                        $component.success(Message.SAVE_SUCCESS)
                    })
                }
            };
            $component.dialog('#modal_edit');
        }

        $scope.editEntity = function (v) {
            $scope.entity = $component.clone(v);
            $scope.editForm = {
                title: WinTitle.EDIT_MENU,
                isEdit: true,
                save: function () {
                    console.log("into edit save method")
                    $component.post($env.url + 'sys/privilege/updateEntity', $scope.entity, function(){
                        $scope.loadData();
                        $component.closeDialog('#modal_edit');
                        $component.success(Message.SAVE_SUCCESS)
                    })
                }
            };

            $component.dialog('#modal_edit');
        }

        $scope.delEntity = function (v, $event) {
            $component.confirm(Message.DEL_CONFIRM, function () {
                $component.post($env.url + 'sys/privilege/delete?id=' + v.id, null, function () {
                    $component.success(Message.DEL_SUCCESS)
                    $scope.loadData();
                })
            })
        }
    }
]);