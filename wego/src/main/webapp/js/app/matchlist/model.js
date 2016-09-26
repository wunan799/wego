var MatchListModel = Class.extend({
    matchList: null,

    init: function () {
    },

    loadMatchList: function (callback) {
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            url: global.api_url + 'match/list.do',
            model: this,

            success: function (result) {
                if (result.errorCode == 0) {
                    this.model.matchList = result.object;
                    callback(result.object);
                } else {
                    $.weui.alert('获取比赛列表失败：' + result.errorMsg);
                }
            }
        });
    },
});
