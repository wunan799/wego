var Controller = Class.extend({
    view: null,
    model: null,

    init: function () {
        this.view = new View();
        this.model = new Model();

        this.model.loadPlayers(function(players) {
            controller.view.showPlayers(players);
        });

        this.initEvent();
    },

    initEvent: function () {
    },
    
    statusChange: function (dom, userId) {
        var status;

        if ($(dom).is(':checked')) {
            status = 1;
        } else {
            status = 0;
        }

        controller.model.signupMatch(userId, status, function (succeed) {
            if (succeed) {
                controller.model.setUserStatus(userId, status);
                controller.view.showPlayerCount(controller.model.getSignupNum()
                    , controller.model.userList.length);
            } else {
                $(dom).attr('checked', (status == 1) ? '' : 'checked');
            }
        });
    }
});

define(function () {
    return {
        setup: function () {
            controller = new Controller();
        }
    }
});
