var MatchListView = Class.extend({
    init: function () {
    },

    showMatchList: function (matchList) {
        var content = '';

        for (var key in matchList) {
            content += '<div class="weui_cell" onclick="matchListController.showMenu(\''
                    + matchList[key].matchId + '\')">' +
                '<div class="weui_cell_hd">' +
                    // '<img class="cover" src="' + matchList[key].avatar + '">' +
                '</div>' +
                '<div class="weui-media__bd weui_cell_primary">' +
                    '<h3 class="title">' + matchList[key].title + '</h3>' +
                    '<p class="summary">' + new Date(matchList[key].time).toLocaleString() + '</p>' +
                '</div>' +
                '<div class="weui_cell_ft"></div></div>';
        }

        $('#matchList').html(content);
    }
});