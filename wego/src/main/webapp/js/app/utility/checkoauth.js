/**
 * Created by wunan on 16-9-23.
 */
(function () {
    var user = window.localStorage.getItem('user');

    if (user == null) {
        window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?' +
            'appid=wx3acffe302f7bce92&redirect_uri=' + encodeURI(window.location.href) +
            '&response_type=code&scope=snsapi_base#wechat_redirect';
    }
})();