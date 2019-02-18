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
})