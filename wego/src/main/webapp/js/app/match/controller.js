var MatchController = Class.extend({
    matchView: null,
    matchModel: null,

    init: function () {
        this.matchView = new MatchView();
        this.matchModel = new MatchModel();
        this.initEvent();
    },

    initEvent: function () {
        $('#submit').click(function () {
            matchController.submit();
        });
    },

    submit: function () {
        var match = this.matchView.getMatchData();
        match.creatorId = this.matchModel.user.userid;
        this.matchModel.saveMatch(match);
    }
});

define(function () {
    return {
        setup: function () {
            global.checkOauth(function (result) {
                if (result) {
                    matchController = new MatchController();
                }
            });
        }
    }
});
