var Model = Class.extend({
    matchList: null,
    userList: null,
    user: null,

    init: function () {
        this.user = global.getLocalParam('user');
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

    delMatch: function (matchId, callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/del.do?matchId=' + matchId,
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
    },

    loadTeam: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + '/department/user/get.do',
            data: {id : this.user.department[0]},
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    this.model.userList = result.object;
                    callback(result.object);
                } else {
                    $.weui.alert('获取球队队员失败：' + result.errorMsg);
                }
            }
        });
    },

});
