<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="java.util.Date" %>
<jsp:useBean id="today" class="java.util.Date"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>osCommand</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">

    <!-- 링크) datepicker 링크   참고한 사이트  -- https://www.daterangepicker.com/ -->
    <script type="text/javascript" src="js/jquery.min_dw.js"></script>
    <script type="text/javascript" src="js/moment.min_dw.js"></script>
    <script type="text/javascript" src="js/daterangepicker.min_dw.js"></script>
    <link rel="stylesheet" type="text/css" href="css/daterangepicker_dw.css" />

    <!-- 링크) jQuery 링크 -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- 링크) 커스텀 페이징 js -->
    <script src="<c:url value='/js/common_dw.js'/>" charset="utf-8"></script>
</head>
<body>
<div style="border: #0f0f0f">
    <form action="checkPing.do" method="GET">
        <div style="display: flex; width: 400px;">
            <input type="text" name="ping">
            <input type="submit" value="ping!">
        </div>
    </form>
    <div>
        ${out}
    </div>
</div>
</body>
</html>
