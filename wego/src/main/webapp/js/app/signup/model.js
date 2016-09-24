var SignupModel = Class.extend({
    user: null,
    matchId: null,

    init: function () {
        this.user = global.getLocalParam('user');
        this.matchId = global.getParameter('match');
    },

    loadMatch: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/get.do?matchId=' + this.matchId,
            model: this,

            success: function (result) {
                callback(result);
            }
        });
    },

    signupMatch: function () {
        var url = global.api_url + 'match/signup.do?matchId='
            + this.matchId + '&userId=' + this.user.userid;

        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: url,
            model: this,

            success: function (result) {
            }
        });
    }
});
