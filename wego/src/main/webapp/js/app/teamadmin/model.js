var Model = Class.extend({
    matchList: null,

    init: function () {
    },

    loadMatch: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/list.do',
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    this.model.matchList = result.object;
                    callback(result.object);
                } else {
                    $.weui.alert('获取比赛列表失败：' + result.errorMsg);
                }
            }
        });
    },

    delMatch: function (match, callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/del.do?matchId=' + match,
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    $.weui.toast('删除成功');
                    callback();
                } else {
                    $.weui.alert('比赛删除失败：' + result.errorMsg);
                }
            }
        });
    }
});
