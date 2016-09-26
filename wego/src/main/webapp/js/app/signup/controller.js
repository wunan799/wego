var SignupController = Class.extend({
    signupView: null,
    signupModel: null,

    init: function () {
        this.signupView = new SignupView();
        this.signupModel = new SignupModel();

        this.signupView.showUser(this.signupModel.user);

        this.signupModel.loadMatch(function(result) {
            signupController.signupView.showMatch(result);
        });

        this.initEvent();
    },

    initEvent: function () {
        $('#submit').click(function () {
            signupController.submit();
        });
    },

    submit: function () {
        this.signupModel.signupMatch();
    }
});

define(function () {
    return {
        setup: function () {
            global.checkOauth(function (result) {
                if (result) {
                    signupController = new SignupController();
                }
            });
        }
    }
});
