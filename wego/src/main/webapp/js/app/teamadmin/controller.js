var Controller = Class.extend({
    view: null,
    model: null,

    init: function () {
        this.view = new View();
        this.model = new Model();
        this.initMatchTab();
        this.initEvent();
    },

    initEvent: function () {
        $('#matchTab').click(function () {
            controller.initMatchTab();
        });

        $('#teamTab').click(function () {
            controller.initTeamTab();
        });
    },

    initMatchTab: function () {
        this.model.loadMatch(function (matchList) {
            controller.view.showMatch(matchList);
        })
    },

    initTeamTab: function () {
        this.model.loadTeam(function (userList) {
            controller.view.showTeam(userList);
        })
    },

    showMatchMenu: function (matchDom, matchId) {
        $.weui.actionSheet([{
            label: '修改比赛',
            onClick: function () {
                $.weui.toast('功能开发中...');
            }
        }, {
            label: '删除比赛',
            onClick: function () {
                $.weui.confirm('确认删除?', function () {
                    controller.model.delMatch(matchId, function () {
                        matchDom.parentElement.removeChild(matchDom);
                    });
                });
            }
        }, {
            label: '管理报名',
            onClick: function () {
                window.location.href = '/signupadmin.html?match=' + matchId;
            }
        }]);
    }
});

define(function () {
    return {
        setup: function () {
            global.checkOauth(function (result) {
                if (result) {
                    controller = new Controller();
                }
            });
        }
    }
});
