app.controller('SqlEditorController', ['$scope', '$rootScope', '$env', '$component', 'w5cValidator',
    function ($scope, $rootScope, $env, $component, w5cValidator) {

        $scope.pageSize = 12;

        $scope.currentPage = 1;

        $scope.$on('$viewContentLoaded', function () {
            console.debug('info', '进入SQL-Editor页面')
            $scope.initEditor();
        });

        $scope.initEditor = function () {
            $scope.editor = CodeMirror.fromTextArea(document.getElementById("code"), {
                mode: 'text/x-sql',
                theme: 'darcula',
                indentWithTabs: true,
                smartIndent: true,
                lineNumbers: true,
                matchBrackets : true,
                autofocus: true,
                maxHighlightLength: 15,
                extraKeys: {"Ctrl-Space": "autocomplete"},
                hintOptions: {tables: {
                        users: ["name", "score", "birthDate"],
                        countries: ["name", "population", "size"]
                    }}
            });
        }
        
        $scope.action = function () {
            $scope.reset();
            $component.post($env.url + 'editor/action', {
                query:$scope.editor.getValue(),
                offset: ($scope.currentPage - 1) * $scope.pageSize,
                limit: $scope.pageSize
            }, function (resp) {
                $scope.process(resp);
            });
        };
        /*处理响应结果*/
        $scope.process = function (resp) {
            if(resp && resp.data){
                $scope.setMessage(resp.data.error);
                /*如果是查询*/
                if(resp.data.type === 0 && !resp.data.error){
                    $scope.temp_headers = [];
                    if(resp.data.result && resp.data.result.rows.length > 0){
                        $scope.rows = resp.data.result.rows;
                        angular.forEach($scope.rows[0], function (v, k) {
                            $scope.temp_headers.push(k)
                        })
                        $scope.headers = $scope.temp_headers.reverse();
                    }
                    $scope.setMessage(null, '执行成功，共查询到' + (resp.data.result ? resp.data.result.total : 0)  + '条数据。耗时' + resp.data.cost + 'ms');
                }else if(resp.data.type === 1){
                    $scope.setMessage(null, '执行成功，共插入到' + resp.data.updateRows  + '条数据。耗时' + resp.data.cost + 'ms');
                }else if(resp.data.type === 2){
                    $scope.setMessage(null, '执行成功，共更新到' + resp.data.updateRows  + '条数据。耗时' + resp.data.cost + 'ms');
                }else if(resp.data.type === 3){
                    $scope.setMessage(null, '执行成功，共删除到' + resp.data.updateRows  + '条数据。耗时' + resp.data.cost + 'ms');
                }
            }
        };
        
        $scope.reset = function () {
            $scope.rows = [];
            $scope.headers = [];
            $scope.error = false;
            $scope.message = undefined;
        }
        
        $scope.setMessage = function (err, msg) {
            if(err){
                $scope.error = true;
                $scope.message = err;
            }else{
                $scope.error = false;
                $scope.message = msg;
            }

        }
}]);