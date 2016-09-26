var PlayersModel = Class.extend({
    user: null,
    matchId: null,

    init: function () {
        this.user = global.getLocalParam('user');
        this.matchId = getQueryString('match');
    },

    loadPlayers: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/players.do?matchId=' + this.matchId,
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    callback(result.object);
                } else {
                    $.weui.alert('获取比赛报名失败：' + result.errorMsg);
                }
            }
        });
    },
});
