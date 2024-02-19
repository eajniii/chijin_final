<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>adminMain</title>

<!-- 부트스트랩 -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/uiii.css" rel="stylesheet">
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">

	.container {
		width: 80%;
		display:flex;
		padding:0px;
	}
	.contents {
		width: 100%;
		margin: 10px;
	}
	.table{
		width: 95%;
		margin: 0 auto;
		text-align: center;
	}

</style>
</head>
<body>

<div class="container">
	<%@include file="/WEB-INF/tiles/adminSide.jsp" %>
	<div class="contents">
		<div class="row" align="center">
	        <div>
	          <h2>관리자 페이지</h2>
	        </div>
		</div>
		<div class="table-responsive">
			<table class="table table-striped-columns">
				<c:forEach items="${dashList}" var="dashList">			
				<tr >
					<td>총 회원 수</td><td>${dashList.M_CNT }명</td>
				</tr>
				<tr>
					<td>신규 주문</td><td>${dashList.O_CNT_A }건</td>
				</tr>
				<tr>
					<td>입금 확인</td><td>${dashList.O_CNT_B }건</td>
				</tr>
				<tr>
					<td>배송 진행</td><td>${dashList.O_CNT_C }건</td>
				</tr>
				<tr>
					<td>수취 확인</td><td>${dashList.O_CNT_D }건</td>
				</tr>
				<tr>
					<td>거래 완료</td><td>${dashList.O_CNT_E }건</td>
				</tr>
				<tr>
					<td>AS신청</td><td>${dashList.AS_CNT }건</td>
				</tr>
				<tr>
					<td>상품Q&A</td><td>${dashList.G_CNT }건</td>
				</tr>
				<tr>
					<td>일반Q&A</td><td>${dashList.Q_CNT }건</td>
				</tr>
				</c:forEach>
			</table>
		</div>

	<!-- /.navbar-header -->
	
	</div>
		<!-- /.sidebar-collapse -->
</div>
	<!-- /.navbar-static-side -->







	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
