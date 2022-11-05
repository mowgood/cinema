<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
	String id=(String)session.getAttribute("id");
	String pw=(String)session.getAttribute("pw");
%> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>CINEMA PLUS</title>
<script type="text/javascript">
	function changeContent(loc, target) {
		document.getElementById("frame").src = loc;
	}
</script>
    <meta name="viewport" id="viewport" content="user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, width=device-width" />
    <link rel="stylesheet" type="text/css" href="./css/default.css">
	<link rel="stylesheet" type="text/css" href="./css/swiper.min.css">
</head>
<body>
	<div id="wrap" class="wrap">
		<!-- 상단영역 -->
		<header id="header" class="header">
			<hgroup>
				<h1><a href="./index.jsp">CINEMA PLUS</a></h1>
				<p>movie theater</p>
			</hgroup>

			<!-- Navigation -->
			<nav class="gnb">
				<ul>
					<li><a href="javascript:changeContent('/CinemaPlus/movie/list.do')" target="_parent" >영화</a></li>
					<li><a href="javascript:changeContent('./index2.html')" target="_parent" >메인</a></li>				
					<li><a href="javascript:changeContent('/CinemaPlus/board/list.do')" target="_parent">게시판</a></li>
					 
					<%if(id==null || id.equals(null)){%> 
						<li><a href="javascript:changeContent('./login.jsp')" target="_parent">로그인</a></li>
					<%} else {%>
						<li><a href="javascript:changeContent('/CinemaPlus/movie/reservationList.do')" target="_parent">예매 내역</a></li>
						<li><a href="logoutAction.jsp" target="_parent" target="_parent">로그아웃</a></li> 
					<%if(id.equals("admin")){%>
  						
					<%}}%>
				</ul>
			</nav>
		</header>
		<hr>
		
		<div class="bg_wrap"></div>


		<!-- 본문 -->
		<section id="container" class="container">


			<!-- mainVisual -->
			<iframe id="frame" src="index2.html"  scrolling="no" frameborder="0" width="1000px" height="2500px">
			<div class="mainVisual">
				<h2>Is Showing</h2>
				<div class="slider">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_01.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_02.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
							</div>
							<div class="swiper-slide">
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_03.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_04.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
							</div>
							<div class="swiper-slide">
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_05.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
								<div class="poster">
									<p>Recommend</p>
									<div class="photo"><img src="./images/img_slide_06.jpg" alt=""></div>
									<dl>
										<dt><!-- 영화 제목 --></dt>
										<dd><a href="#">View details</a></dd>
									</dl>
								</div>
							</div>
					   </div>
					</div>
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>
				</div>
			</div>


			<div class="movie_wrap">
				<div class="inner">
					<h2>Scheduled For Release</h2>
					
					<p>
						<!-- 영화 제목 -->><br>
						<!-- contents -->
					</p>
					
					<video id="myVideo" src="./images/movie.mp4" controls></video>
					<div class="btn_more"><a href="#">Read More</a></div>
				</div>
			</div>


			<div class="review_wrap">
				<h2>Reviews</h2>
				<ul>
					<li>
						<p>
							<!-- contents -->
						</p>
						<em><!-- 영화 제목 -->></em>
						<span><!-- 작성자 --></span>
					</li>
					<li>
						<p>
							<!-- contents -->
						</p>
						<em><!-- 영화 제목 --></em>
						<span><!-- 작성자 --></span>
					</li>
					<li>
						<p>
							<!-- contents -->
						</p>
						<em><!-- 영화 제목 -->></em>
						<span><!-- 작성자 --></span>
					</li>
				</ul>
			</div>		
			</iframe>
		</section>
		
	 	<!-- 페이지 푸터 -->
		<footer id="footer" class="footer">
			<div class="inner">
				<div class="subscribe">
					<h2>Join Our Mailing List</h2>
					<form action="">
						<fieldset>
							<legend>Mailing List</legend>
							<input type="text" placeholder="Enter your email here*" required>
							<button type="submit">Subscribe Now</button>
						</fieldset>
					</form>
                </div>
				<div class="foot_nav">
					<ul>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Shipping & returns</a></li>
						<li><a href="#">Store Policy</a></li>
						<li><a href="#">Payment Methods</a></li>
					</ul>
					<div class="info">
						<ul>
							<li><a href="#" target="_blank">Instagram</a></li>
							<li><a href="#" target="_blank">Facebook</a></li>
							<li><a href="#" target="_blank">Youtube</a></li>
						</ul>
					</div>
				</div>
                <div class="copyright">&copy; 2021 by CINEMA PLUS. All rights reserved</div>
			</div>
		</footer>
	</div>

	<script src="./js/jquery-3.2.1.min.js"></script>
	<script src="./js/jquery.easing.1.3.js"></script>
	<script src="./js/swiper.min.js"></script>
	<script src="./js/common.js"></script>
    
</body>
</html>
