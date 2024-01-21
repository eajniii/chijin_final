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
<title>MY_Side</title>

<!-- 부트스트랩 -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/db619109b0.js" crossorigin="anonymous"></script>

<style>
.sidebar{
	width: 270px; 
	height: 1000px; 
	top:130px; 
	align:left;
}
.mypage-top{
	width: 100%;
	height: 130px;
	background-color: #F78181;
	text-align: center;
	margin-top: 10px;
}
.nav-second-level>li>a{
	width:200px;
}
</style>
</head>
<body>

	<div class="navbar-default sidebar" role="navigation">
		
		<div class="mypage-top">
			<br />
			<h3>마이페이지</h3>
			<p>MYPAGE</p>
		</div>

		<div style="width: 100%;height: 50px; text-align: center;">
		<br />
			<p style="text-align: center;">${SESSION_NAME}님 반갑습니다 ^^/</p>
		</div>
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
			
				<li><a href="#" style="background: #F8F8F8;">
						<b class="fa fa-bar-chart-o">나의 쇼핑정보</b> <span class="fa arrow">▼</span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="/myOrderList.do">- 주문/배송 내역</a></li>
						<li><a href="/myAsList.do">- 교환/환불/AS 내역</a></li>
						<li><a href="/my/myPointList.do">- 포인트</a></li>
						<li><a href="/my/myCouponList.do">- 할인쿠폰</a></li>
						<li><a href="/my/myLikeList.do">- 찜 상품</a></li>

					</ul></li>

				<li class="active">
				<a href="#" style="background: #F8F8F8;">
						<b class="fa fa-bar-chart-o ">나의 문의내역</b> <span class="fa arrow">▼</span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="/my/openMyReview.do">- 나의 상품평</a></li>
						<li><a href="/my/openMyGoodsQna.do">- 나의 Q&A글</a></li>						
					</ul>
				</li>

				<li class="active">
				<a href="#" style="background: #F8F8F8;">
						<span class="fa fa-bar-chart-o"><b>개인정보</b></span> <span class="fa arrow">▼</span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="/my/memberModify.do">- 회원정보수정</a></li>
						<li><a href="/my/memberDelete.do">- 회원 탈퇴</a></li>						
					</ul>
				</li>	

			</ul>
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
