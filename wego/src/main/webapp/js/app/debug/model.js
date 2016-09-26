var DebugModel = Class.extend({
    user: null,
    matchId: null,

    init: function () {
        this.user = global.getLocalParam('user');
        this.matchId = getCookie('match');
    },

    loadMatch: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/get.do?matchId=' + this.matchId,
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    callback(result.object);
                } else {
                    var dlg = new ModalDlg();
                    $.weui.alert('获取比赛失败：' + result.errorMsg);
                }
            }
        });
    },

    sebugMatch: function () {
        var url = global.api_url + 'match/sebug.do?matchId='
            + this.matchId + '&userId=' + this.user.userid;

        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: url,
            model: this,

            success: function (result) {
                if (result.errorCode != 0) {
                    $.weui.alert('比赛报名失败：' + result.errorMsg);
                } else {
                    $.weui.toast('报名成功');
                }
            }
        });
    }
});
