var MatchView = Class.extend({
    titleDom: $('#title'),
    timeDom: $('#time'),

    init: function () {
         this.timeDom.val(new Date().toLocaleString());
    },

    getMatchData: function () {
        var match = {};
        match.title = this.titleDom.val();
        match.content = $('#remark').val();
        var date = new Date(this.timeDom.val());
        match.time = date.getTime() + date.getTimezoneOffset() * 60000;
        match.pitch = $('#pitch').val();
        match.pitchAddress = $('#pitchAddress').val();
        match.opponent = $('#opponet').val();
        match.shirtColor = $("#shirtColor").find("option:selected").text();
        return match;
    }
});