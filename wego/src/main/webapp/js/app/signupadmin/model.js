var Model = Class.extend({
    user: null,
    matchId: null,
    userList: null,

    init: function () {
        this.user = global.getLocalParam('user');
        this.matchId = getQueryString('match');
    },

    loadPlayers: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + '/match/team/players.do',
            data: {matchId : this.matchId, teamId : this.user.department[0]},
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

    signupMatch: function (userId, status, callback) {
        var url = global.api_url + (status == 0
                ? 'match/signout.do' : 'match/signin.do');

        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: url,
            model: this,
            data: {matchId: this.matchId, userId: userId},

            success: function (result) {
                if (result.errorCode != 0) {
                    $.weui.alert('操作失败：' + result.errorMsg);
                } else {
                    // $.weui.toast('操作成功');
                }

                callback(result.errorCode == 0);
            }
        });
    },

    setUserStatus: function (userId, status) {
        for (var i = 0; i < this.userList.length; ++i) {
            if (userId == this.userList[i].userid) {
                this.userList[i].status = status;
                break;
            }
        }
    },

    getSignupNum: function () {
        var num = 0;

        for (var i = 0; i < this.userList.length; ++i) {
            if (this.userList[i].status == 1) {
                ++num;
            }
        }

        return num;
    }
});
