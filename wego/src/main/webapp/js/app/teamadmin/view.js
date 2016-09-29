var View = Class.extend({
    init: function () {
    },

    showMatch: function (matchList) {
        var content = '';

        for (var key in matchList) {
            content += '<div class="weui_cell" onclick="controller.showMatchMenu(this, \''
                    + matchList[key].matchId + '\')">' +
                '<div class="weui_cell_hd">' +
                    // '<img class="cover" src="' + matchList[key].avatar + '">' +
                '</div>' +
                '<div class="weui-media__bd weui_cell_primary">' +
                    '<h3 class="title">' + matchList[key].title + '</h3>' +
                    '<p class="summary">比赛时间：' + dateToString(new Date(matchList[key].time)) + '</p>' +
                '</div>' +
                '<div class="weui_cell_ft"></div></div>';
        }

        $('#matchList').html(content);
    },

    showTeam: function (userList) {
        var content = '';

        for (var key in userList) {
            content += '<div class="weui_cell">' +
                '<div class="weui_cell_hd">' +
                    '<img class="cover" src="' + userList[key].avatar + '">' +
                '</div>' +
                '<div class="weui-media__bd weui_cell_primary">' +
                    '<h3 class="title">' + userList[key].name + '</h3>' +
                    '<p class="summary">' + userList[key].mobile + '</p>' +
                '</div></div>';
        }

        $('#team').html(content);
        $('#teamTitle').html('队员列表：共' + userList.length + '人');
    }
});