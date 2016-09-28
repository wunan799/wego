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

        $('#testOauth').click(function() {
            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?' +
                'appid=wx3acffe302f7bce92&redirect_uri=' +
                'http%3a%2f%2fwego.au-syd.mybluemix.net%2fapi%2fuser%2fcallback.do' +
                '&response_type=code&scope=snsapi_base&state=' +
                btoa(window.location.pathname + window.location.search) + '#wechat_redirect';
            window.location.href = url;
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
