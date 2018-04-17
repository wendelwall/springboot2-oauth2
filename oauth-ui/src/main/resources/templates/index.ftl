<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <button id="aaa">click here</button>
</body>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#aaa").click(function () {
            $.ajax({
                url: "http://localhost:8085/user",
                type: "GET",
                success: function (data) {
                    document.write(data);
                }
            });
        });
    });
</script>
</html>