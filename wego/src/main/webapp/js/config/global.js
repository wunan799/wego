var Global = Class.extend({
    api_url: '/api/',

    init: function () {
        $.ajaxSetup({
            beforeSend: function () {
                $.weui.loading("正在加载");
            },

            complete: function (event, xhr, options) {
                $.weui.hideLoading();

                if (event.status != 200) {
                    $.weui.alert('获取数据失败: '
                        + event.responseJSON.errorMsg, {title: '出错了'});
                }
            }
        });
    },

    getParameter: function (param, defaultValue) {
        var value = window.sessionStorage.getItem(param);

        if (value == undefined) {
            return defaultValue;
        }

        try {
            var obj = JSON.parse(value);

            if (obj != null) {
                return obj;
            } else {
                return value;
            }
        } catch (e) {
            console.log(e);
            return value;
        }
    },

    setParameter: function (param, value) {
        if (value == undefined) {
            return;
        }

        if (value instanceof Object) {
            window.sessionStorage.setItem(param, JSON.stringify(value));
        } else {
            window.sessionStorage.setItem(param, value);
        }
    },

    getLocalParam: function (param, defaultValue) {
        var value = window.localStorage.getItem(param);

        if (value == undefined) {
            return defaultValue;
        }

        try {
            var obj = JSON.parse(value);

            if (obj != null) {
                return obj;
            } else {
                return value;
            }
        } catch (e) {
            console.log(e);
            return value;
        }
    },

    setLocalParam: function (param, value) {
        if (value == undefined) {
            return;
        }

        if (value instanceof Object) {
            window.localStorage.setItem(param, JSON.stringify(value));
        } else {
            window.localStorage.setItem(param, value);
        }
    },

    delParameter: function (param) {
        window.sessionStorage.removeItem(param);
    },

    delLocalParam: function (param) {
        window.localStorage.removeItem(param);
    },

    checkOauth: function (callback) {
        var user = this.getLocalParam('user');

        if (user != null) {
            callback(true);
            return;
        }

        $.weui.loading('获取授权');
        var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?' +
            'appid=wx3acffe302f7bce92&redirect_uri=' +
            'http%3a%2f%2fwego.au-syd.mybluemix.net%2fapi%2fuser%2fcallback.do' +
            '&response_type=code&scope=snsapi_base&state=' +
            btoa(window.location.pathname + window.location.search) + '#wechat_redirect';
        var iframe = document.createElement('iframe');
        iframe.id = 'iframe1';
        iframe.name = 'iframe1';
        iframe.src = url;
        iframe.width = 0;
        iframe.height = 0;
        document.body.appendChild(iframe);
        var totalTime = 0;

        this.checkResult = function (time) {
            if (time > 5000) {
                $.weui.hideLoading();
                $.weui.alert('获取授权超时，请刷新重试');
                callback(false);
                return;
            }

            if (global.getLocalParam('user') == null) {
                time += 100;
                setTimeout('global.checkResult(' + time + ')', 100);
            } else {
                $.weui.hideLoading();
                callback(true);
            }
        };

        this.checkResult(totalTime);
    },
});

var global = new Global();