var SignupView = Class.extend({
    userName: $('#userName'),
    userPhone: $('#userPhone'),

    init: function () {
    },

    showUser: function (user) {
        this.userName.val(user.name);
        this.userPhone.val(user.mobile);
    },

    showMatch: function (match) {
        $('#title').val(match.title);
        $('#time').val(dateToString(new Date(match.time)));
        $('#pitch').val(match.pitch);
        $('#opponent').val(match.opponent);
        $('#remark').val(match.content);
        $('#shirt').val(match.shirtColor);
        this.changeState(match.signed);
    },

    changeState: function (signed) {
        if (signed) {
            $('#submit').html('取消报名')
        } else {
            $('#submit').html('报名')
        }
    }
});