var View = Class.extend({
    init: function () {
    },

    showPlayers: function (players) {
        var content = '';
        var signNum = 0;

        for (var key in players) {
            if (players[key].status == 1) {
                ++signNum;
            }

            content += '<div class="weui_cell weui_cell_switch">' +
                '<div class="weui_cell_hd">' +
                    '<img class="cover" src="' + players[key].avatar + '">' +
                '</div>' +
                '<div class="weui_media_bd weui_cell_primary">' +
                    '<h3 class="title">'+ players[key].name + '</h3>' +
                    '<p class="summary">' + players[key].mobile + '</p>' +
                '</div>' +
                '<div class="weui_cell__ft">' +
                    '<input class="weui_switch" type="checkbox" ' +
                        'onchange="controller.statusChange(this, \'' + players[key].userid + '\')"'+
                        (players[key].status == 1 ? ' checked="checked">' : '>') +
                '</div></div>';
        }

        this.showPlayerCount(signNum, players.length);
        $('#players').html(content);
    },

    showPlayerCount: function (signNum, count) {
        $('#title').html('报名球员列表：共' + signNum + '/' + count + '人报名');
    }
});