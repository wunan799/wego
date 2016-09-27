var PlayersView = Class.extend({
    init: function () {
    },

    showPlayers: function (players) {
        var content = '';

        for (var key in players) {
            content += '<div class="weui_cell"><div class="weui_cell_hd">' +
                '<img class="cover" src="' + players[key].avatar + '"></div>';
            content += '<div class="weui-media__bd weui_cell_primary"><h3 class="title">'
                + players[key].name + '</h3><p class="summary">' + players[key].mobile
                + '</p></div></div>';
        }

        $('#title').html('报名球员列表：共' + players.length + '人报名');
        $('#players').html(content);
    }
});