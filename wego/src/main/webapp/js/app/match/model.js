var MatchModel = Class.extend({
    init: function () {
    },

    saveMatch: function (match) {
        $.ajax({
            type: 'post',
            url: global.api_url + 'match/add.do',
            model: this,
            contentType: 'application/json',
            data: JSON.stringify(match),

            success: function (result) {
                alert(result);
            }
        });
    }
});
