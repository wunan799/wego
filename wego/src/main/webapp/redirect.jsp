<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%--<% String target = (String) request.getAttribute("redirect"); %>--%>
<%--<jsp:include page=""></jsp:include>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跳转</title>
</head>
<body>
<script type="text/javascript">
    window.localStorage.setItem('user', '${user}');

    if (self == top) {
        window.location.replace('${redirect}');
    }
</script>
</body>
</html>