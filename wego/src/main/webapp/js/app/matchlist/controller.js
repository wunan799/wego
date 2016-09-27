var MatchListController = Class.extend({
    matchListView: null,
    matchListModel: null,

    init: function () {
        this.matchListView = new MatchListView();
        this.matchListModel = new MatchListModel();

        this.matchListModel.loadMatchList(function(result) {
            matchListController.matchListView.showMatchList(result);
        });

        this.initEvent();
    },

    initEvent: function () {
    },

    showMenu: function (matchId) {
        $.weui.actionSheet([{
            label: '比赛报名',
            onClick: function (){
                window.location.href = '/signup.html?match=' + matchId;
            }
        },{
            label: '查看报名',
            onClick: function (){
                window.location.href = '/players.html?match=' + matchId;
            }
        },{
            label: '比赛详情',
            onClick: function (){
            }
        }]);
    }
});

define(function () {
    return {
        setup: function () {
            matchListController = new MatchListController();
        }
    }
});
