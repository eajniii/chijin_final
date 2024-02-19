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
	<title>adminSide</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/db619109b0.js" crossorigin="anonymous"></script>
<style>
.sidebar{
	width: 300px; 
	height: 1000px; 
	top:130px; 
	align:left;
	background-color:#f5f5f5;
}
.adminpage-top{
	width: 100%;
	height: 130px;
	background-color: #a4a4b0;
	text-align: center;
	margin-top: 10px;
}
#side-menu>li>a{
	width:200px;
}
</style>
</head>
<body>

<!-- /.navbar-header -->
<div class="navbar-default sidebar" role="navigation">
	<div class="adminpage-top">
		<br />
		<h2><img src="/img/ad.png" style="width : 100%"></h2>
		<%--		<h2>관리자</h2>--%>
		<%--		<p>Admin</p>--%>
	</div>
	
	<div class="sidebar-nav navbar-collapse" >
		<ul class="nav" id="side-menu">
			<%--				<li><a href="javascript:pingTest()"--%>
			<%--					   style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">--%>
			<%--					<b class="fa fa-dashboard">서버관리</b> --%>
			<%--				</a></li>--%>

			<li><a href="/checkPing.do" style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">
				<b class="fa fa-dashboard">서버관리</b> ⚙️
			</a></li>

			<li><a href="/main.do" style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">
				<b class="fa fa-dashboard">쇼핑몰로 이동</b>
			</a></li>

			<li><a href="/order_admin_a.do"
				   style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">
				<b class="fa fa-dashboard">주문배송관리</b>
			</a></li>

			<li><a href="/as_admin.do" style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">
				<b class="fa fa-dashboard">교환/환불/AS</b>
			</a></li>

			<!-- <li class="active"> 포지션 -->

			<li><a href="#" style="background: #e7e7e7;">
				<b class="fa fa-bar-chart-o">상품관리</b> <span class="fa arrow">▼</span>
			</a>
				<ul class="nav nav-second-level">
					<li><a href="">- 상품목록/수정</a></li>
					<li><a href="/shop/openGoodsWrite.do">- 상품등록</a></li>
					<!-- <li><a href="">- 할인 적용</a></li> -->
				</ul></li>


			<li><a href="/member_admin.do"
				   style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">
				<b class="fa fa-dashboard">회원관리</b>
			</a></li>

			<li class="active"><a href="#" style="background: #e7e7e7;">
				<b class="fa fa-bar-chart-o">게시판관리</b> <span class="fa arrow">▼</span>
			</a>
				<ul class="nav nav-second-level">
					<li><a href="/notice/openNoticeList.do">- 공지사항</a></li>
					<li><a href="/faq/openFaqList.do">- FAQ</a></li>
					<li><a href="/qna/openQnaList.do">- 상품Q&A</a></li>
				</ul></li>

			<!-- s 쿠폰 추가 2020.06.25 -->
			<li class="active">
				<a href="#" style="background: #e7e7e7;">
					<b class="fa fa-bar-chart-o">쿠폰관리</b> <span class="fa arrow">▼</span>
				</a>
				<ul class="nav nav-second-level">
					<li><a href="/adminCouponList.do">- 쿠폰목록/수정</a></li>
					<li><a href="/adminCouponWriteForm.do">- 쿠폰등록</a></li>
				</ul>
			</li>
			<!-- e 쿠폰 추가 2020.06.25 -->

			<!-- s 이벤트 추가 2020.06.30 -->
			<li class="active">
				<a href="#" style="background: #e7e7e7;">
					<b class="fa fa-bar-chart-o">이벤트관리</b> <span class="fa arrow">▼</span>
				</a>
				<ul class="nav nav-second-level">
					<li><a href="/adminEventList.do">- 이벤트목록/수정</a></li>
					<li><a href="/adminEventWriteForm.do">- 이벤트등록</a></li>
				</ul>
			</li>
			<!-- e 이벤트 추가 2020.06.30 -->
			<%--				<li><a href="/serverCheck.do"--%>
			<%--					   style="background: #e7e7e7; border-bottom: 1px solid #F8F8F8;">--%>
			<%--					<b class="fa fa-dashboard">서버관리</b> --%>
			<%--				</a></li>--%>



		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->


<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->

<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="js/bootstrap.min.js"></script>
<script>
	function pingTest() {
		var url = "/checkPing.do";
		var name = "ping test";
		var option = "width=400, height=600, top=100, left=200, location=no"
		window.open(url, name, option);
	}
</script>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>
