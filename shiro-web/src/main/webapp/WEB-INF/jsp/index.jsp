<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/base.jsp" %>
    <title>首页</title>
</head>
<body>
<h2>${msg}</h2>
<a href="javascript:void(0)" id="cross">test cros</a>


<script type="text/javascript">
    $(function () {
        $('#cross').click(function () {
            $.get('http://127.0.0.1:8080/api', function (data) {
                console.dir(data);
            }, 'json');

        });
    });
</script>

</body>
</html>