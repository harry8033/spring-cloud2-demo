app.filter('m_state', function(){
    return function (input, uppercase) {
        var out = '';
        if (input == 0) {
            out = '禁用'
        } else if (input == 1) {
            out = '启用';
        }
        return out;
    }
}).filter('menu_type', function(){
    return function (input, uppercase) {
        var out = '';
        if (input == 0) {
            out = '目录'
        } else if (input == 1) {
            out = '菜单';
        }
        return out;
    }
}).filter('bl_type', function(){
    return function (input, uppercase) {
        var out = false;
        if (input == 1) {
            out = true;
        }
        return out;
    }
});