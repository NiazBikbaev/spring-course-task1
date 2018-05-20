<%--
  Created by IntelliJ IDEA.
  User: Niyaz_Bikbaev
  Date: 5/19/2018
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="state">

</div>
<p/>
<div>
    <button onclick="onGet()">GET</button>
</div>
<p/>
<div>
    <button onclick="onPut()">PUT</button>
    Enter id:
    <input id="putInputId" type="text"/>
    New Data value:
    <input id="putInputData" type="text"/>
</div>
<p/>
<div>
    <button onclick="onPost()">POST</button>
    <input id="postInput" type="text"/>
</div>
<p/>
<div>
    <button onclick="onDelete()">DELETE</button>
    Delete by id:
    <input id="deleteInputId" type="text"/>
</div>

<script src="/webjars/jquery/3.3.1-1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function onPost() {
        var postInput = $("#postInput").val();
        $.ajax({
            method: "post",
            data: JSON.stringify({id: null, data: postInput}),
            url: "/form",
            success: function (result) {
                $("#state").html(result);
            }
        });
    }

    function onPut() {
        var putInput = $("#putInputData").val();
        var putId = $("#putInputId").val();
        $.ajax({
            method: "put",
            data: JSON.stringify({id: putId, data: putInput}),
            url: "/form",
            success: function (result) {
                $("#state").html(result);
            }
        });
    }

    function onGet() {
        $.ajax({
            method: "get",
            url: "/form",
            success: function (result) {
                $("#state").html(result);
            }
        });
    }

    function onDelete() {
        var deleteId = $("#deleteInputId").val();
        $.ajax({
            method: "delete",
            data: JSON.stringify({id: deleteId, data: null}),
            url: "/form",
            success: function (result) {
                $("#state").html(result);
            }
        });
    }

</script>

</body>
</html>
