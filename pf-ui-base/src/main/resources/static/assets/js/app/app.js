var app = angular.module("app", ["ui.router", "oc.lazyLoad", "ngSanitize", "ui.bootstrap.pagination", "w5c.validator"]);


app.config(['$controllerProvider', function ($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use
    // it
    // in new ones!
    $controllerProvider.allowGlobals();
}]);

app.controller('IndexController', ['$scope', '$rootScope', '$http', '$location',
    function ($scope, $rootScope, $http, $location) {
    //目录菜单点击事件
    $scope.menuClick = function ($event) {
        app.menuEvent($event)
    }
    
}]);

app.fetchMenu = function(menus, $stateProvider, parent){
    angular.forEach(menus, function (item) {
        $stateProvider.state(item.name, {
            url: item.url,
            templateUrl: item.templateUrl,
            data: {
                title: item.title,
                parent: parent ? parent.title : ""
            },
            controller: item.controller,
            resolve: {
                deps: [
                    '$ocLazyLoad',
                    function ($ocLazyLoad) {
                        return $ocLazyLoad
                            .load({
                                name: 'app',
                                insertBefore: '#ng_load_plugins_before', // load
                                files: item.files
                            });
                    }]
            }
        })
        if(item.children){
            app.fetchMenu(item.children, $stateProvider, item)
        }
    })
}

app.config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            console.log("info", "配置菜单")
            //console.log("info", $scope)
            $urlRouterProvider.otherwise("/dashboard");
            $stateProvider
                .state(
                    'dashboard',
                    {
                        url: "/dashboard",
                        templateUrl: "views/dashboard.html",
                        data: {
                            title: '首页'
                        }
                    })
        }
]).provider('$systemRouter', function ($stateProvider) {
    this.$get = function ($http, $state) {
        return {
            setRouters : function($rootScope){
                console.log("debug", "动态添加菜单")
                //var menus = [];
                //$rootScope.data
                $http.get('assets/json/menu.json').success(function (data) {
                    $rootScope.menus = data;
                    angular.forEach(data, function (item) {
                        if(item.children)
                            app.fetchMenu(item.children, $stateProvider, item)
                    });
                });
            },

        }
    };
}).provider('$component', function () {
    var danger = function (response) {
        var msg = '系统发生未知异常，请联系管理员';
        if(response && response.msg){
            msg = response.msg;
        }
        $.notify(msg, {
            type:'danger',
            placement: {
                from: "top",
                align: "right"
            }
        });
    }
    this.$get = function ($http) {
        return {
            //GET请求
            get: function (url, success) {
                return $http.get(url).error(function (response) {
                    danger(response);
                }).success(function (resp) {
                    if (resp && resp.code) {
                        if (resp.code != 200){
                            $.notify(Message['FAIL_' + resp.code], {
                                type:'warn',
                                placement: {
                                    from: "top",
                                    align: "right"
                                }
                            });
                        }else{
                            success(resp);
                        }
                    }else {
                        danger(Message.RETURN_DATA_ERROR);
                    }
                });
            },
            //POST请求
            post: function (url, body, success) {
                return $http.post(url, body).error(function (response) {
                    danger(response);
                }).success(function (resp) {
                    if (resp && resp.code) {
                        if (resp.code != 200){
                            $.notify(Message['FAIL_' + resp.code], {
                                type:'warn',
                                placement: {
                                    from: "top",
                                    align: "right"
                                }
                            });
                        }else{
                            success(resp);
                        }
                    }
                });
            },
            //提示框
            alert: function (config) {
                bootbox.alert(config);
            },
            //确认框
            confirm: function (message, succ, cancel) {
                bootbox.dialog({
                    title: '<i class="fa fa-question-circle"></i>  确认提示',
                    message: message,
                    buttons: {
                        confirm: {
                            label: '<i class="fa fa-check"></i> 确认',
                            className: 'btn btn-warning btn-sm',
                            callback: function () {
                                if (succ) succ()
                            }
                        },
                        cancel: {
                            label: '<i class="fa fa-times"></i> 取消',
                            className: 'btn btn-default btn-sm',
                            callback: function () {
                                if (cancel) cancel()
                            }
                        }
                    }
                })
            },
            dialog: function(selector){
                return angular.element(selector).modal('show');
            },
            closeDialog: function(selector){
                angular.element(selector).modal('hide');
            },
            danger: function (msg) {
                danger(msg)
            },
            //错误提示
            error: function (msg) {
                $.notify(msg, {
                    type:'danger',
                    placement: {
                        from: "top",
                        align: "right"
                    }
                });
            },
            warn: function (msg) {
                $.notify(msg, {
                    type:'warn',
                    placement: {
                        from: "top",
                        align: "right"
                    }
                });
            },
            //成功提示框
            success: function (msg) {
                $.notify(msg, {
                    type:'success',
                    placement: {
                        from: "top",
                        align: "right"
                    },
                    delay: 1000
                });
            },
            clone: function (p) {
                return JSON.parse(JSON.stringify(p));
            }
        }
    }
}).run(["$rootScope", "$state",'$systemRouter', function ($rootScope, $state, $systemRouter) {
    $rootScope.$state = $state; // state to be accessed from view
    $systemRouter.setRouters($rootScope)
    console.log("info", "app run")
}]).value('$env', {url: "http://localhost:8080/demo/"});