<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跳转</title>
</head>
<body>
<script type="text/javascript">
    var checkOauth = <%=request.getParameter("oauth")%>;
    var url = '<%=request.getParameter("target")%>';

    //设置参数
    try {
        var params = JSON.parse('<%=request.getParameter("params")%>');

        for (var key in params) {
            window.sessionStorage.setItem(key, params[key]);
        }
    } catch (e) {
        console.log(e);
    }

    if (checkOauth == 1) {
        var user = window.localStorage.getItem('user');

        if (user == null) {
            url = 'https://open.weixin.qq.com/connect/oauth2/authorize?'
                    + 'appid=wx3acffe302f7bce92&redirect_uri='
                    + encodeURI(window.location.host + "/api/user/get.do")
                    + '&response_type=code&scope=snsapi_base&state='
                    + btoa(url) + '#wechat_redirect';
        }
    }

    window.location.href = url;
</script>
</body>
</html>