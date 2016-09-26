/**
 * Created by wunan on 16-9-23.
 */
var OAUTH = {
    checker: function () {
        this.check = function () {
            var user = window.localStorage.getItem('user');

            if (user != null) {
                return;
            }

            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?' +
                'appid=wx3acffe302f7bce92&redirect_uri=' +
                'http%3a%2f%2fwego.au-syd.mybluemix.net%2fapi%2fuser%2fcallback.do' +
                '&response_type=code&scope=snsapi_base&state=' +
                btoa(window.location.pathname + window.location.search) + '#wechat_redirect';
            var iframe = document.createElement('iframe');
            iframe.id = 'iframe1';
            iframe.name = 'iframe1';
            iframe.src = url;
            document.body.appendChild(iframe);
            // var userParam = this.getQueryString('user');
            //
            // if (userParam != null) {
            //     window.localStorage.setItem('user', userParam);
            //     return;
            // }
            //
            // window.location.href = url;
        };
    }
};

(function () {
    var checker = new OAUTH.checker();
    checker.check();
})();