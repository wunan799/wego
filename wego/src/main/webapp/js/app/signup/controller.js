var SignupController = Class.extend({
    signupView: null,
    signupModel: null,

    init: function () {
        this.signupView = new SignupView();
        this.signupModel = new SignupModel();

        global.getOauthUser(this, function(controller, result) {
            controller.signupModel.user = result;
            controller.signupView.showUser(result);
        });

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
            signupController = new SignupController();
        }
    }
});
