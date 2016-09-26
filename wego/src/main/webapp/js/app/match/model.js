var MatchModel = Class.extend({
    user: null,

    init: function () {
        this.user = global.getLocalParam('user');
    },

    saveMatch: function (match) {
        $.ajax({
            type: 'post',
            url: global.api_url + 'match/add.do',
            model: this,
            contentType: 'application/json',
            data: JSON.stringify(match),

            success: function (result) {
                if (result.errorCode == 0) {
                    $.weui.toast('添加比赛成功');
                } else {
                    $.weui.alert('添加比赛失败：' + result.errorMsg);
                }
            }
        });
    }
});
