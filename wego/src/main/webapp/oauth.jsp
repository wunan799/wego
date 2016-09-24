<%--
  Created by IntelliJ IDEA.
  User: wunan
  Date: 16-9-21
  Time: 下午3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>身份验证</title>
  </head>
  <body>
    <script type="text/javascript">
      var user = '${user}';
      window.localStorage.setItem('user', user);
      var url = '${target}';
      window.location.href = url;
    </script>
  </body>
</html>
