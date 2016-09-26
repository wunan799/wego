var PlayersController = Class.extend({
    playersView: null,
    playersModel: null,

    init: function () {
        this.playersView = new PlayersView();
        this.playersModel = new PlayersModel();

        this.playersModel.loadPlayers(function(result) {
            playersController.playersView.showPlayers(result);
        });

        this.initEvent();
    },

    initEvent: function () {
    },
});

define(function () {
    return {
        setup: function () {
            playersController = new PlayersController();
        }
    }
});
