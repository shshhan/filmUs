<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page session = "false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FILMUS</title>
	<link rel="icon" href="/resources/img/favicon_noback.ico" type="image/x-icon">

    <script>
        function goBack(){
        	console.log("goBack clicked.");
  			history.go(-1);
        }//goBack
    </script>
</head>
<body>
    <h1>예상치 못한 오류가 발생했습니다.</h1>
    <h1>불편을 드려 죄송합니다.</h1>
    <button onclick="javascript:goBack()">돌아가기</button>

</body>
</html>
