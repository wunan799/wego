var SignupModel = Class.extend({
    user: null,
    match: null,

    init: function () {
        this.user = global.getLocalParam('user');
        this.match= {matchId : getQueryString('match'), signed : false};
    },

    loadMatch: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/get.do?matchId=' + this.match.matchId,
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    this.model.match = result.object;

                    for (var i = 0; i < this.model.match.playerList.length; ++i) {
                        var playerId = this.model.match.playerList[i];

                        if (this.model.user.userid == playerId) {
                            this.model.match.signed = true;
                            break;
                        }
                    }

                    callback(this.model.match);
                } else {
                    $.weui.alert('获取比赛失败：' + result.errorMsg);
                }
            }
        });
    },

    signupMatch: function (callback) {
        var url = global.api_url + (this.match.signed
                ? 'match/signout.do' :'match/signin.do');

        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: url,
            model: this,
            data: {matchId : this.match.matchId, userId : this.user.userid},

            success: function (result) {
                if (result.errorCode != 0) {
                    $.weui.alert('操作失败：' + result.errorMsg);
                } else {
                    $.weui.toast('操作成功');
                    this.model.match.signed = !this.model.match.signed;
                }

                callback(result.errorCode == 0);
            }
        });
    }
});
