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
        this.matchModel.saveMatch(match);
    }
});

define(function () {
    return {
        setup: function () {
            matchController = new MatchController();
        }
    }
});
