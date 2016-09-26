var DebugController = Class.extend({
    debugView: null,
    debugModel: null,

    init: function () {
        this.debugView = new DebugView();
        this.debugModel = new DebugModel();
        this.initEvent();
    },

    initEvent: function () {
        $('#clearCache').click(function () {
            global.delLocalParam('user');
            $.weui.toast('清除完成');
        });

        $('#showCache').click(function () {
            var user = global.getLocalParam('user');
            $.weui.dialog({
                title: '缓存内容',
                content: JSON.stringify(user)
            });
        });
    },

    submit: function () {
        this.debugModel.debugMatch();
    }
});

define(function () {
    return {
        setup: function () {
            debugController = new DebugController();
        }
    }
});
